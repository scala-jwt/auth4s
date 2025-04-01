package io.auth4s

import scala.quoted.*

object Auth4sEnumMacro {

  private def validateEnumType[E](using q: Quotes, t: Type[E]): q.reflect.TypeRepr = {
    import q.reflect.*

    val repr = TypeRepr.of[E](using t)

    if (!repr.classSymbol.exists(_.flags is Flags.Enum)) {
      report.errorAndAbort(
        "You can only use derive Auth4sEnum on 'enums'"
      )
    }

    repr
  }

  private def getSubClasses(q: Quotes)(repr: q.reflect.TypeRepr): List[q.reflect.TypeRepr] = {
    val children = repr.classSymbol.get.children.map(_.typeRef)
    children.toList
  }

//  private def buildSeqExpr[E](q: Quotes)(
//      subclasses: Seq[q.reflect.TypeRepr]
//  )(using tpe: Type[E]): Expr[IndexedSeq[E]] = {
//    given quotes: q.type = q
//    import q.reflect.*
//
//    if (subclasses.isEmpty) {
//      '{ IndexedSeq.empty[E] }
//    } else {
//      val valueExprs = subclasses.map { sub =>
//        Ref(sub.typeSymbol.companionModule).asExprOf[E]
//      }
//
//      val values = Expr.ofSeq(valueExprs)
//
//      '{
//        IndexedSeq[E](${ values }: _*)
//      }
//    }
//  }

  def derived[E](using q: Quotes, t: Type[E]): Expr[String] = {

    given repr: q.reflect.TypeRepr             = validateEnumType[E]
    given subClasses: List[q.reflect.TypeRepr] = getSubClasses(q)(repr)

    subClasses.map(_.asExprOf[E])

    Expr(s"size ${subClasses.size}")
//    buildSeqExpr[E](q)(subClasses)

//    Expr("Hello")

//    val enum = TypeRepr.of[E].typeSymbol
//    val cases = enum.caseFields.map { field =>
//      val name = field.name
//      val value = name
//        .replaceFirst("([A-Z])", "_$1")
//        .toLowerCase
//        .replaceFirst("^_", "")
//        .toUpperCase
//      s"""$name -> "$value""""
//    }
//
//    val code = s"""Map(${cases.mkString(", ")})"""

//    Expr("code")
  }
}
