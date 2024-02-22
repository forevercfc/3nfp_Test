package com.ex.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ex.springboot.dao.ISimpleBbsDao;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyController {

    @Autowired
    ISimpleBbsDao dao;
    
    @RequestMapping("/")
    public String root() throws Exception {
        return "redirect:/list";
    }
    
    // 페이징 처리를 포함한 listPage 메소드
    @RequestMapping("/list")
    public String listPage(Model model, @RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="type", defaultValue="") String type, @RequestParam(value="word", defaultValue="") String word) {
        int limit = 5; // 페이지 당 게시글 수
        int totalCount = dao.getBoardCount(type, word); // 전체 게시글 수
        int totalPage = (int)Math.ceil((double)totalCount / limit); // 전체 페이지 수
        int startPage = ((page - 1) / 10) * 10 + 1; // 시작 페이지 번호
        int endPage = startPage + 9; // 끝 페이지 번호
        if(endPage > totalPage) {
            endPage = totalPage;
        }
        
        model.addAttribute("list", dao.listDao(page, limit, type, word));
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        model.addAttribute("word", word);
        model.addAttribute("type", type);
        
        return "board/list";
    }
    
    @RequestMapping("/view")
    public String view(HttpServletRequest request, Model model) {
        String sId = request.getParameter("id");
        model.addAttribute("dto", dao.viewDao(sId));
        return "board/view";
    }
    
    @RequestMapping("/writeForm")
    public String writeForm() {
        return "board/writeForm";
    }
    
    @RequestMapping("/write")
    public String write(HttpServletRequest request, Model model) {
        dao.writeDao(request.getParameter("writer"), request.getParameter("title"), request.getParameter("content"));
        return "redirect:/list";
    }
    
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, Model model) {
        dao.delectDao(request.getParameter("id"));
        return "redirect:/list";
    }
    
    @RequestMapping("/updateForm")
    public String updateForm(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        model.addAttribute("dto", dao.viewDao(id));
        return "board/updateForm";
    }
    
    @RequestMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        String writer = request.getParameter("writer");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        dao.updateDao(id, writer, title, content);
        return "redirect:/view?id=" + id; // 수정된 게시물 보기로 리다이렉트
    }



    
    
    
    
}
