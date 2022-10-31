package com.exe.cozy.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.exe.cozy.domain.*;
import com.exe.cozy.service.*;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.exe.cozy.domain.ItemDetailDto;
import com.exe.cozy.domain.ItemDetailInsertDto;
import com.exe.cozy.domain.ItemQuestionDto;
import com.exe.cozy.domain.ItemAnswerDto;
import com.exe.cozy.domain.ReplyDto;
import com.exe.cozy.service.ItemDetailService;
import com.exe.cozy.service.ItemQuestionService;
import com.exe.cozy.service.ItemAnswerService;
import com.exe.cozy.service.ReplyService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.exe.cozy.util.MyPage;

@Controller
public class ItemDetailController {

	@Resource
	private ItemDetailService itemDetailService;
	@Resource
	private ReplyService replyService;
	@Resource
	private ItemQuestionService itemQuestionService;
	
	@Resource
	private ItemAnswerService itemAnswerService;

	@Resource
	private CartService cartService;
	

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
	public ModelAndView createItem_ok(ItemDetailInsertDto idto, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> insertMap = new HashMap<String, Object>();

		List<MultipartFile> fileList = new LinkedList<MultipartFile>();
		fileList.add(idto.getItemImage1());
		fileList.add(idto.getItemImage2());
		fileList.add(idto.getItemImage3());
		fileList.add(idto.getItemImage4());
		fileList.add(idto.getDetailImage());

		int i = 0;
		for (MultipartFile file : fileList) {
			i++;
			if (file == null || file.isEmpty()) {
				continue;
			}
			int rstKey = itemDetailService.fileWrite(file);

			insertMap.put("image" + i, String.valueOf(rstKey));
		}

		int itemMaxNum = itemDetailService.itemMaxNum() + 1;

		insertMap.put("itemNum", itemMaxNum);
		insertMap.put("itemName", idto.getItemName());
		insertMap.put("itemMainType", idto.getItemMainType());
		insertMap.put("itemSubType", idto.getItemSubType());
		insertMap.put("itemPrice", idto.getItemPrice());
		insertMap.put("itemDiscount", idto.getItemDiscount());
		insertMap.put("itemContent", idto.getItemContent());
		insertMap.put("itemHitcount", 0);
		insertMap.put("itemStock", idto.getItemStock());
		insertMap.put("itemState", idto.getItemState());
		insertMap.put("todaydeal", idto.getTodaydeal());
		insertMap.put("itemColor", idto.getItemColor());
		insertMap.put("itemSize", idto.getItemSize());

		itemDetailService.insertItem(insertMap);

		/*
		 * int itemNum = Integer.parseInt(request.getParameter("itemNum")); String
		 * pageNum = request.getParameter("pageNum"); String searchValue =
		 * request.getParameter("searchValue");
		 * 
		 * if(searchValue!=null && !searchValue.equals("")) { searchValue =
		 * URLDecoder.decode(searchValue,"UTF-8");}
		 */

		// mav.setViewName("redirect:/itemDetail?pageNum=\"+pageNum");

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
	public ModelAndView detail(HttpServletRequest request, Principal principal) throws Exception {
		int itemNum = Integer.parseInt(request.getParameter("itemNum"));

		//test 종료시 한번에 시큐리티 예정

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

		itemDetailService.updateItemHitCount(itemNum);

		ItemDetailDto idto = itemDetailService.getReadItemData(itemNum);
		List<ReplyDto> rdtoList = replyService.getReadReplyData(itemNum);
		//List<ItemQnaDto> qdtoList = itemQnaService.getReadItemQnaData(itemNum);
		List<ItemQuestionDto> qdtoList = itemQuestionService.getReadQnaList(itemNum);
		//List<QnaAnswerDto> adtoList = qnaAnswerService.getReadQnaAnswerData(itemNum);
		List<CartDto> clist = cartService.listCart(principal.getName());

		if (idto == null) {
			ModelAndView mav = new ModelAndView();
			// 일단은 index 로 리다이렉트 시키기
			mav.setViewName("redirect:index");
			/*
			 * 상품 리스트 페이지 완성되면 주석 지우기 mav.setViewName("redirect:/index?pageNum="+pageNum);
			 */
			return mav;

		}
		/*
		 * String searchValue = request.getParameter("searchValue");
		 * 
		 * if(searchValue!=null && !searchValue.equals("")) { searchValue =
		 * URLDecoder.decode(searchValue,"UTF-8");}
		 */
		
		
		//DB Split사용
		//사이즈 옵션 불러오기
		String itemSizeList = idto.getItemSize();
		String [] itemSizes = itemSizeList.split(",");
		
		//컬러옵션 불러오기
		String itemColorList = idto.getItemColor();
		String [] itemColors = itemColorList.split(",");

		int salePrice = idto.getItemPrice() - idto.getItemDiscount();

		// System.out.println(salePrice);
		ModelAndView mav = new ModelAndView();

		// System.out.println(rdtoList.get(0).getRegDate());
		mav.addObject("idto", idto);
		mav.addObject("rdtoList", rdtoList);
		mav.addObject("qdtoList",qdtoList);
		//mav.addObject("adtoList",adtoList);
		mav.addObject("itemSizes",itemSizes);
		mav.addObject("itemColors",itemColors);
		mav.addObject("salePrice", salePrice);
		mav.addObject("clist", clist);

		// mav.addObject("params",param);
		// mav.addObject("pageNum", pageNum);

		mav.setViewName("itemDetail");
		return mav;

	}
	//questionWrite_ok
	@PostMapping("/qnaWrite_ok")
    public ModelAndView reviewWrite_ok(ItemQuestionDto qdto, HttpServletRequest request) throws Exception{

        ModelAndView mav = new ModelAndView();
        
        int itemQnaMaxNum = itemQuestionService.itemQnaMaxNum();
        qdto.setItemQueNum(itemQnaMaxNum +1);
       
        //itemQuestionService
        itemQuestionService.insertItemQna(qdto);
        mav.setViewName("redirect:/");
        return mav;
    }
	// 문의답변창으로 이동 itemAnswer로 변경
		@GetMapping("itemQnaAnswer") /** itemQna update view */
		public ModelAndView reviewUpdate(ItemQuestionDto qdto, HttpServletRequest request) throws Exception {
			int itemQueNum = Integer.parseInt(request.getParameter("itemQueNum"));

			
			
			qdto = itemQuestionService.findItemQna(itemQueNum);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("itemQnaAnswer");
			mav.addObject("ItemQuestionDto", qdto);


			return mav;
		}

		// 문의답변 itemAnswer로변경
		@PostMapping("itemQnaAnswer_ok")
		public ModelAndView itemQnaAnswer_ok(ItemAnswerDto adto, HttpServletRequest request) throws Exception {

			ModelAndView mav = new ModelAndView();
			
			int qnaAnswerMaxNum = itemAnswerService.qnaAnswerMaxNum();
			adto.setItemAnsNum(qnaAnswerMaxNum + 1);
			
			itemAnswerService.insertQnaAnswer(adto);
			

			
			// System.out.println(rdto.getRating());
			// System.out.println(rdto.getContent());
			mav.setViewName("redirect:/");
			return mav;
		}
		
		
		
		// 문의삭제 deleteQuestion
		@GetMapping("deleteQna")
		public ModelAndView deleteItemQna(ItemQuestionDto qdto, HttpServletRequest request) throws Exception {
			int itemQueNum = Integer.parseInt(request.getParameter("itemQueNum"));

			ModelAndView mav = new ModelAndView();
			itemQuestionService.deleteItemQna(itemQueNum);
			mav.setViewName("redirect:/");
			return mav;
		}

	
}
