// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

using System;
using System.Collections.Generic;
using System.ComponentModel.Composition;
using System.Threading.Tasks;
using Microsoft.CodeAnalysis;
using Microsoft.Generator.CSharp.Input;
using Microsoft.Generator.CSharp.Primitives;
using Microsoft.Generator.CSharp.Providers;
using Microsoft.Generator.CSharp.SourceInput;

namespace Microsoft.Generator.CSharp
{
    /// <summary>
    /// Base class for code model plugins. This class is exported via MEF and can be implemented by an inherited plugin class.
    /// </summary>
    [InheritedExport]
    [Export(typeof(CodeModelPlugin))]
    [ExportMetadata("PluginName", nameof(CodeModelPlugin))]
    public abstract class CodeModelPlugin
    {
        private List<LibraryVisitor> _visitors = [];
        private List<MetadataReference> _additionalMetadataReferences = [];
        private static CodeModelPlugin? _instance;
        private List<string> _sharedSourceDirectories = [];
        internal static CodeModelPlugin Instance
        {
            get
            {
                return _instance ?? throw new InvalidOperationException("CodeModelPlugin is not initialized");
            }
            set
            {
                _instance = value;
            }
        }

        public Configuration Configuration { get; }

        public IReadOnlyList<LibraryVisitor> Visitors => _visitors;

        [ImportingConstructor]
        public CodeModelPlugin(GeneratorContext context)
        {
            Configuration = context.Configuration;
            _inputLibrary = new(() => new InputLibrary(Instance.Configuration.OutputDirectory));
            TypeFactory = new TypeFactory();
        }

        // for mocking
#pragma warning disable CS8618 // Non-nullable field must contain a non-null value when exiting constructor. Consider declaring as nullable.
        protected CodeModelPlugin()
#pragma warning restore CS8618 // Non-nullable field must contain a non-null value when exiting constructor. Consider declaring as nullable.
        {
        }

        internal bool IsNewProject { get; set; }
        private Lazy<InputLibrary> _inputLibrary;

        // Extensibility points to be implemented by a plugin
        public virtual TypeFactory TypeFactory { get; }
        public virtual SourceInputModel SourceInputModel => _sourceInputModel ?? throw new InvalidOperationException($"SourceInputModel has not been initialized yet");
        public virtual string LicenseString => string.Empty;
        public virtual OutputLibrary OutputLibrary { get; } = new();
        public virtual InputLibrary InputLibrary => _inputLibrary.Value;
        public virtual TypeProviderWriter GetWriter(TypeProvider provider) => new(provider);
        public IReadOnlyList<MetadataReference> AdditionalMetadataReferences => _additionalMetadataReferences;

        public IReadOnlyList<string> SharedSourceDirectories => _sharedSourceDirectories;

        public virtual void Configure()
        {
        }

        public void AddVisitor(LibraryVisitor visitor)
        {
            _visitors.Add(visitor);
        }

        public void AddMetadataReference(MetadataReference reference)
        {
            _additionalMetadataReferences.Add(reference);
        }

        public void AddSharedSourceDirectory(string sharedSourceDirectory)
        {
            _sharedSourceDirectories.Add(sharedSourceDirectory);
        }

        private SourceInputModel? _sourceInputModel;

        internal async Task InitializeSourceInputModelAsync()
        {
            GeneratedCodeWorkspace existingCode = GeneratedCodeWorkspace.CreateExistingCodeProject([Instance.Configuration.ProjectDirectory], Instance.Configuration.ProjectGeneratedDirectory);
            _sourceInputModel =  new SourceInputModel(await existingCode.GetCompilationAsync());
        }

        internal HashSet<string> TypesToKeep { get; } = new();
        //TODO consider using TypeProvider so we can have a fully qualified name to filter on
        //https://github.com/microsoft/typespec/issues/4418
        public void AddTypeToKeep(string typeName)
        {
            TypesToKeep.Add(typeName);
        }
    }
}
