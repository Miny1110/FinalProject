package com.exe.cozy.util;

import org.springframework.stereotype.Service;

@Service
public class MyPage {
	//전체 페이지의 갯수 구하기
	public int getPageCount(int numPerPage,int dataCount) {
		int pageCount =0;
		
		pageCount = dataCount / numPerPage; //34/3
		
		if(dataCount % numPerPage !=0) {
			pageCount++;
			
		}
		return pageCount; //총페이지 반환
	}

	
	//페이징 처리 메소드
	public String pageIndexList(int currentPage,int totalPage,String listUrl) {
		
		int numPerBlock = 5;//◀이전 6 7 8 9 10 다음 ▶ 이거 갯수를 말한다. 
		int currentPageSetup;// 이건 이전에 들어가는 값 ! 이전을 누르면 어디로가야하는지 이런거
		int page;
		
		StringBuffer sb = new StringBuffer(); // 누적시킬거라 쓰레기값 적게생기고 빠른 스트링값 버퍼 쓴다.
		
		if(currentPage==0||totalPage==0) {
			return ""; //반환값이 스트링이라 널값을 가지고 반환해야해
		}
		//돌아가는 주소 만드는거!
		//list.jsp
		//list.jsp?serchKey=subject%serchValue=111
		if(listUrl.indexOf("?")!=-1) {
			listUrl =listUrl+"&";
		}else {
			listUrl = listUrl + "?";
			//list.jsp?pageNum=3 이렇게 하기위해 ? 를 붙이는거!
			//물음표가 있는 상태는 검색을 한 상태에는 ? 를 가지고있다                                           
		}
		
		//◀이전 6 7 8 9 10 다음 ▶ 의 숫자 구하기!
		//◀이전 11 12 13 14 15 다음 ▶ >> 이건 공식!
		currentPageSetup =(currentPage/numPerBlock)*numPerBlock; 
		
		if(currentPage%numPerBlock==0) {
			currentPageSetup=currentPageSetup-numPerBlock;
		}
		//◀이전
		if(totalPage>numPerBlock && currentPageSetup>0) {
			sb.append("<a href=\""+listUrl+"pageNum="+currentPageSetup+"\">◀이전</a>&nbsp;");
			//<a href="list.jsp?pageNum=5">◀이전</a>&nbsp; 이런의미 
			
		}
		//◀이전 6 7 8 9 10 다음 ▶ 
		//◀이전 11 12 13 14 15 다음 ▶ 
		//바로가기 페이지 만들기 저 숫자 만들기!!
		
		page = currentPageSetup+1; //시작하는 페이지번호
		
		
		while(page<=totalPage&&page <= (currentPageSetup + numPerBlock)) { //내가 찍고자하는 페이지보다 작거나 같을때까지 뿌려라
			
			if(page==currentPage) {
				sb.append("<font color=\"Fuchsia\">"+page+"</font>&nbsp;");
				//<font color="Fuchsia">9</font>&nbsp;
				
			}else {
				sb.append("<a href=\"" + listUrl +"pageNum=" + page +"\">"+page+"</a>&nbsp;");
				//<a href ="list.jsp"?pageNum=8">8</a>&nbsp;
			}
			page++; //페이지가 계속 증가해야하니까!
			
			
		}
		//◀이전 6 7 8 9 10 다음 ▶
		//◀이전 11 12 다음이 보이거나 안보이거나 하게 해야해
		if(totalPage-currentPageSetup>numPerBlock) {
			
			sb.append("<a href=\""+listUrl +"pageNum="+page +"\">다음▶</a>&nbsp;");
			//<a href="list.jsp?pageNum=11">다음 ▶</a>&nbsp;
			
		}
	return sb.toString();
	}


}
