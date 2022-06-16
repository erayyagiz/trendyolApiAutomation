package com.trendyol.apiautomation.controller;

import static com.trendyol.apiautomation.model.ResultBuilder.getResult;
import static com.trendyol.apiautomation.model.ResultBuilder.getResultFromError;

import com.trendyol.apiautomation.model.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CalculatorController {

    @PostMapping(path = "/add")
    public Result add(@RequestParam Double[] params){
        double total=0;
        for (int i=0;i<params.length;i++){
            total=params[i]+total;
        }
        return getResult(total);
    }

    @PostMapping(path = "/subtract")
    public Result subtract(@RequestParam Double[] params){
        if (params.length>2) {
            return getResultFromError("Much params!");
        }
        return getResult(params[0]-params[1]);
    }

    @PostMapping(path = "/multiplication")
    public Result multiplication(@RequestParam Double[] params){
        double total=1;
        for (int i=0;i<params.length;i++){
            total=params[i]*total;
        }
        return getResult(total);
    }

    @PostMapping(path = "/division")
    public Result division(@RequestParam Double[] params){
        if (params.length>2) {
            return getResultFromError("Much params!");
        }
        if (params[1]==0) {
            return getResultFromError("No division by null!");
        }
        return getResult(params[0]/params[1]);
    }

    @GetMapping(path = "/sum")
    public Result sum(@RequestParam int params){
        int total=0;
        for(int i = params ; i>=0; i--){
            total=total+i;
        }
        return getResult(total);
    }
}
