package com.trendyol.apiautomation.model;

import java.math.BigDecimal;

public class ResultBuilder {

   private ResultBuilder() {
   }


   public static Result getResult(Object result ) {
      return Result.withResult(result.toString());
   }

   public static Result getResult(BigDecimal result ) {
      return Result.withResult(result.toPlainString());
   }

   public static Result getResultFromError(String errorMessage) {
      return Result.withError(errorMessage);
   }
}
