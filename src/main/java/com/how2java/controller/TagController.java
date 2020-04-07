package com.how2java.controller;

import com.how2java.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping("/pushTag")
    public void pushTag(@RequestParam("tag") String tag,
                        @RequestParam("fileCloudToken") String fileCloudToken) {
        this.tagService.pushTag(tag, fileCloudToken);
    }

    @RequestMapping("/getTags")
    public List<String> getTags(@RequestParam("fileCloudToken") String fileCloudToken) {
        return this.tagService.getTags(fileCloudToken);
    }
}
