package com.example.tone_analyser_api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/analyse")
public class ApiController {

    @PostMapping
    public int[] Tone_Analysed(@RequestBody String String_val)
    {
        return Main.FindSentiment(String_val);
    }
}
