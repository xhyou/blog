package com.blog.blogProduct.web.admin;

import com.blog.blogProduct.po.Tag;
import com.blog.blogProduct.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 查询所有的标签
     * @return
     */
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    /**
     * 跳转新增页面
     */
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    /**
     * 新增页面
     */
    @PostMapping("/tags")
    public String addTag(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag t = tagService.findByName(tag.getName());
        if(t!=null){
            result.rejectValue("name","errorCode","不能重复添加相同的标签");
        }
        if(result.hasErrors()){
            return "admin/tags-input";
        }
        Tag resultTag = tagService.saveTag(tag);
        if(resultTag!=null){
            attributes.addFlashAttribute("message","新增成功");
        }else{
           attributes.addFlashAttribute("message","新增失败");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 跳转编辑页面
     */
    @GetMapping("/tags/{id}/input")
    public String editPage(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";

    }

    /**
     * 编辑页面
     */
    @PostMapping("/tags/{id}")
    public String editTag(@Valid Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        Tag t = tagService.findByName(tag.getName());
        if(t!=null){
            result.rejectValue("name","errorCode","不能重复添加相同的标签");
        }
        if(result.hasErrors()){
            return "admin/tags-input";
        }
        Tag resultTag = tagService.updateTag(id,tag);
        if(resultTag!=null){
            attributes.addFlashAttribute("message","修改成功");
        }else{
            attributes.addFlashAttribute("message","修改失败");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 删除页面
     */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id){
        tagService.deleteTag(id);
        return "redirect:/admin/tags";
    }

}
