package com.exe.cozy.itemDetail;

import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.domain.ReplyDto;
import com.exe.cozy.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

@Controller
public class ItemDetailController {

	@Resource
	private ItemDetailService itemDetailService;
	@Resource
	private ReplyService replyService;

	@Autowired
	MyPage myPage;

	// 상품등록 창으로 이동하는거
	@GetMapping("createItem") /** item insert view */
	public ModelAndView createItem() throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("createItem");
		return mav;
	}

	// 상품등록
	@PostMapping("createItem_ok")
	public ModelAndView createItem_ok(ItemDetailDto idto, HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView();
		int itemMaxNum = itemDetailService.itemMaxNum();

		idto.setItemNum(itemMaxNum + 1);
		itemDetailService.insertItem(idto);

		/*
		int itemNum = Integer.parseInt(request.getParameter("itemNum"));
		String pageNum = request.getParameter("pageNum");
		String searchValue = request.getParameter("searchValue");
		 
		if(searchValue!=null && !searchValue.equals("")) { searchValue =
		URLDecoder.decode(searchValue,"UTF-8");}
		*/

		//mav.setViewName("redirect:/itemDetail?pageNum=\"+pageNum");
		
		mav.setViewName("redirect:/");
		return mav;
	}

	// 리뷰수정창으로 이동
	@GetMapping("reviewUpdate") /** itemreview update view */
	public ModelAndView reviewUpdate(ReplyDto rdto, HttpServletRequest request) throws Exception {
		int replyId = Integer.parseInt(request.getParameter("replyId"));

		// System.out.println("replyId:" + replyId);
		rdto = replyService.findReply(replyId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reviewUpdate");
		mav.addObject("replyDto", rdto);

		return mav;
	}

	// 리뷰수정
	@PostMapping("reviewUpdate_ok")
	public ModelAndView reviewUpdate_ok(ReplyDto rdto, HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView();

		replyService.updateReply(rdto);
		// System.out.println(rdto.getRating());
		// System.out.println(rdto.getContent());
		mav.setViewName("redirect:/");
		return mav;
	}

	// 리뷰삭제
	@GetMapping("deleteReply")
	public ModelAndView deleteReply(ReplyDto rdto, HttpServletRequest request) throws Exception {
		int replyId = Integer.parseInt(request.getParameter("replyId"));

		ModelAndView mav = new ModelAndView();
		replyService.deleteReply(replyId);
		mav.setViewName("redirect:/");
		return mav;
	}

	@RequestMapping("itemDetail") /** item 상세페에지 view */
	public ModelAndView detail(HttpServletRequest request) throws Exception {
		int itemNum = Integer.parseInt(request.getParameter("itemNum"));
		/*
		 * detail 페이지 완성되면 이거 풀기 String pageNum = request.getParameter("pageNum");
		 */
		/*
		 * search 완성되면 이거 풀기 String searchKey = request.getParameter("searchKey");
		 * String searchValue = request.getParameter("searchValue");
		 * 
		 * if(searchValue!=null && !searchValue.equals("")) { searchValue =
		 * URLDecoder.decode(searchValue,"UTF-8");}
		 */

		// itemDetailService.updateItemHitCount(itemNum);

		ItemDetailDto idto = itemDetailService.getReadItemData(itemNum);
		List<ReplyDto> rdtoList = replyService.getReadReplyData(itemNum);
		if (idto == null) {
			ModelAndView mav = new ModelAndView();
			// 일단은 index 로 리다이렉트 시키기
			mav.setViewName("redirect:index");
			/*
			 * 상품 리스트 페이지 완성되면 주석 지우기 mav.setViewName("redirect:/index?pageNum="+pageNum);
			 */
			return mav;

		}
		/*String searchValue = request.getParameter("searchValue");
		 * 
		 * if(searchValue!=null && !searchValue.equals("")) { searchValue =
		 * URLDecoder.decode(searchValue,"UTF-8");}
		 */

		int salePrice = idto.getItemPrice() - idto.getItemDiscount();

		// System.out.println(salePrice);
		ModelAndView mav = new ModelAndView();

		// System.out.println(rdtoList.get(0).getRegDate());
		mav.addObject("idto", idto);
		mav.addObject("rdtoList", rdtoList);
		mav.addObject("salePrice", salePrice);

		// mav.addObject("params",param);
		// mav.addObject("pageNum", pageNum);

		mav.setViewName("itemDetail");
		return mav;

	}

}
