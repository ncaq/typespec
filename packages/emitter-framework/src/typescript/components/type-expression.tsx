import { ValueExpression } from "@alloy-js/typescript";
import { IntrinsicType, Model, Scalar, Type } from "@typespec/compiler";
import { isDeclaration } from "../../core/utils/typeguards.js";

export interface TypeExpressionProps {
  type: Type;
}

export function TypeExpression({ type }: TypeExpressionProps) {
  if (isDeclaration(type) && !(type as Model).indexer) {
    // todo: probably need abstraction around deciding what's a declaration in the output
    // (it may not correspond to things which are declarations in TypeSpec?)
    // return <Reference refkey={type} />;
    throw new Error("Reference not implemented");
  }

  switch (type.kind) {
    case "Scalar":
    case "Intrinsic":
      return <>{getScalarIntrinsicExpression(type)}</>;
    case "Boolean":
    case "Number":
    case "String":
      return <ValueExpression jsValue={type.value} />;
    case "Union":
      throw new Error("UnionExpression not implemented");
    // return <UnionExpression type={type} />;
    case "Tuple":
      return (
        <>
          [
          {type.values.map((element) => (
            <>
              <TypeExpression type={element} />,
            </>
          ))}
          ]
        </>
      );
    case "EnumMember":
      return (
        <>
          {type.enum.name}.{type.name}
        </>
      );
    case "Model":
      // if (isArray(type)) {
      //   const elementType = type.indexer.value;
      //   return <ArrayExpression elementType={elementType} />;
      // }

      // if (isRecord(type)) {
      //   const elementType = type.indexer.value;
      //   return <RecordExpression elementType={elementType} />;
      // }

      // return <InterfaceExpression type={type} />;
      throw new Error("ModelExpression not implemented");

    default:
      throw new Error(type.kind + " not supported in TypeExpression");
  }
}

const intrinsicNameToTSType = new Map<string, string>([
  ["unknown", "unknown"],
  ["string", "string"],
  ["int32", "number"],
  ["int16", "number"],
  ["float16", "number"],
  ["integer", "number"],
  ["float", "number"],
  ["float32", "number"],
  ["int64", "bigint"],
  ["boolean", "boolean"],
  ["null", "null"],
  ["void", "void"],
  ["numeric", "number"],
  ["uint64", "number"], // TODO: bigint?
  ["uint32", "number"],
  ["uint16", "number"],
  ["bytes", "Uint8Array"],
  ["float64", "number"], // TODO: bigint?
  ["safeint", "number"],
  ["utcDateTime", "Date"],
  ["url", "string"],
]);

function getScalarIntrinsicExpression(type: Scalar | IntrinsicType): string {
  if (type.kind === "Scalar" && type.baseScalar && type.namespace?.name !== "TypeSpec") {
    // This is a delcared scalar
    throw new Error("Declared scalar not implemented");
    // return <Reference refkey={type} />;
  }
  const tsType = intrinsicNameToTSType.get(type.name);
  if (!tsType) {
    throw new Error(`Unknown scalar type ${type.name}`);
  }
  return tsType;
}
