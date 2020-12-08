<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<% response.setHeader("P3P","CP='CAO PSA CONi OTR OUR DEM ONL'");%>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="../css/uikit.css">
	<link rel="stylesheet" href="../css/uikit-rtl.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css">
	<link rel="stylesheet" href="../css/style.css">
	<title>Document</title>
</head>

<body>
	<div id="QnA">
		<div class="wrapper">
			<div class="qna-top">
				<select id="cntPerPage" name="sel" onchange="selChange()">
					<option value="5"
							<c:if test="${paging.cntPerPage == 5}">selected</c:if>>5줄 보기</option>
					<option value="10"
							<c:if test="${paging.cntPerPage == 10}">selected</c:if>>10줄 보기</option>
					<option value="15"
							<c:if test="${paging.cntPerPage == 15}">selected</c:if>>15줄 보기</option>
					<option value="20"
							<c:if test="${paging.cntPerPage == 20}">selected</c:if>>20줄 보기</option>
				</select>
				<select name="" id="">
					<option value="">검색조건</option>
				</select>



				<button class="write-btn" type="button" uk-toggle="target: #quesrion-write-modal">+ 글쓰기</button>
			</div>



			<div class="qna-search">
				<input type="search" placeholder="검색어입력" />
				<button class="search-btn"><span class="uk-margin-small-right" uk-icon="search"></span></button>
			</div>



			<ul class="qna-list" uk-accordion="multiple: true">
				<c:forEach items="${qna}" var="qna">
			    <li>
					<c:choose>
						<c:when test="${qna.qna_secret eq 'Y'}">
							<span class="user-profile"><img src="../img/component.png" alt=""></span>비밀글 입니다.
						</c:when>
						<c:otherwise>
							<a class="uk-accordion-title" href="#">
								<span class="user-profile"><img src="../img/component.png" alt=""></span>
									${qna.qna_title}
								<p>${qna.qna_name}</p>
							</a>

							<div class="uk-accordion-content">
								<div class="amend-delete">
									<a class="amend-btn" uk-icon="pencil" href="#"></a>
									<a class="delete-btn" uk-icon="trash" href="#"></a>
								</div>
								<div class="question-content">
									<p class="qna-cont">
										${qna.qna_contents}
									</p>

									<div class="file">
										<a href="#"><span uk-icon="folder"></span> 화면캡쳐.jpg</a>
									</div>
								</div>
								<div class="comment-wrap">
									<input type="text" placeholder="답변작성" />
									<button>게시</button>
								</div>

								<div class="comment-list">
									<div class="comment-box">

									<span class="user-profile">
										<span class="profile-img"><img src="../img/component2.png" alt=""></span><br/>
										<span class="id-level">홍길동 교수</span>
									</span>

										<span class="commnet-text">
										<span>
											공지사항에 링크있습니다. 확인해보세요<br/>
											아안 안아 낭 ㅏㄴㅇ ㅏㄴ아 나아
										</span>
									</span>
									</div>
									<div class="comment-amend">
										<a class="amend-btn" uk-icon="pencil" href="#"></a>
										<a class="delete-btn" uk-icon="trash" href="#"></a>
									</div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
			    </li>
				</c:forEach>
			    <li>
			        <a class="uk-accordion-title" href="#">
			        	<span class="user-profile"><img src="../img/component.png" alt=""></span>
			        	디자인기획 과제제출 언제까지인가요?
			        </a>
			        <div class="uk-accordion-content">
			        	<div class="amend-delete">
				        	<a class="amend-btn" uk-icon="pencil" href="#"></a>
				        	<a class="delete-btn" uk-icon="trash" href="#"></a>
			        	</div>
			        	<div class="question-content">
			            	<p class="qna-cont">
			            		성적 확인은 어디서 하나요? 모르겠어요 아아 ㅏㅇ낭 ㅏㄴ으 ㅏ느아 느아ㅡ 낭 ㅡ<br/>
			            		rkskskskskskksksksksk<br/>
			            		ㄴ아ㅓㅁ나ㅓ옴너ㅏ오ㅓㅏㅁ노아ㅓㄴㅁ오
			            	</p>
			            	<div class="file">
			            		<a href="#"><span uk-icon="folder"></span> 화면캡쳐.jpg</a>
			            	</div>
			            </div>
			            <div class="comment-wrap">
			            	<input type="text" placeholder="답변작성" />
			            	<button>게시</button>
			            </div>

			            <div class="comment-list">
			            	<div class="comment-box">

				            	<span class="user-profile">
				            		<span class="profile-img"><img src="../img/component2.png" alt=""></span><br/>
				            		<span class="id-level">홍길동 교수</span>
				            	</span>

				            	<span class="commnet-text">
				            		<span>
				            			공지사항에 링크있습니다. 확인해보세요<br/>
				            			아안 안아 낭 ㅏㄴㅇ ㅏㄴ아 나아
				            		</span>
				            	</span>
				            </div>
				            <div class="comment-amend">
				            	<a class="amend-btn" uk-icon="pencil" href="#"></a>
				        		<a class="delete-btn" uk-icon="trash" href="#"></a>
				            </div>
			            </div>
			        </div>
			    </li>

			    <li>
			        <a class="uk-accordion-title" href="#">
			        	<span class="user-profile"><img src="../img/component.png" alt=""></span>
			        	졸업작품관련 질문드립니다
			        </a>
			        <div class="uk-accordion-content">
			        	<div class="amend-delete">
				        	<a class="amend-btn" uk-icon="pencil" href="#"></a>
				        	<a class="delete-btn" uk-icon="trash" href="#"></a>
			        	</div>
			        	<div class="question-content">
			            	<p class="qna-cont">
			            		성적 확인은 어디서 하나요? 모르겠어요 아아 ㅏㅇ낭 ㅏㄴ으 ㅏ느아 느아ㅡ 낭 ㅡ<br/>
			            		rkskskskskskksksksksk<br/>
			            		ㄴ아ㅓㅁ나ㅓ옴너ㅏ오ㅓㅏㅁ노아ㅓㄴㅁ오
			            	</p>
			            	<div class="file">
			            		<a href="#"><span uk-icon="folder"></span> 화면캡쳐.jpg</a>
			            	</div>
			            </div>
			            <div class="comment-wrap">
			            	<input type="text" placeholder="답변작성" />
			            	<button>게시</button>
			            </div>

			            <div class="comment-list">
			            	<div class="comment-box">

				            	<span class="user-profile">
				            		<span class="profile-img"><img src="../img/component2.png" alt=""></span><br/>
				            		<span class="id-level">홍길동 교수</span>
				            	</span>

				            	<span class="commnet-text">
				            		<span>
				            			공지사항에 링크있습니다. 확인해보세요<br/>
				            			아안 안아 낭 ㅏㄴㅇ ㅏㄴ아 나아
				            		</span>
				            	</span>
				            </div>
				            <div class="comment-amend">
				            	<a class="amend-btn" uk-icon="pencil" href="#"></a>
				        		<a class="delete-btn" uk-icon="trash" href="#"></a>
				            </div>
			            </div>
			        </div>
			    </li>

			</ul>

			<div class="paging">
				<ul>
					<li><a href="#" uk-icon="chevron-left"></a></li>
					<li><a class="active" href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#" uk-icon="chevron-right"></a></li>
				</ul>
				<c:if test="${paging.startPage != 1 }">
					<a href="/helloWorld?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a>
				</c:if>
				<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
					<c:choose>
						<c:when test="${p == paging.nowPage }">
							<b>${p }</b>
						</c:when>
						<c:when test="${p != paging.nowPage }">
							<a href="/helloWorld?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
						</c:when>
					</c:choose>
				</c:forEach>
				<c:if test="${paging.endPage != paging.lastPage}">
					<a href="/helloWorld?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
				</c:if>
			</div>
		</div>
	</div>

	<div id="quesrion-write-modal" uk-modal>
	    <div class="uk-modal-dialog">
	        <button class="uk-modal-close-default" type="button" uk-close></button>
	        <div class="uk-modal-header">
	            <h2 class="uk-modal-title">질문등록</h2>
	        </div>
	        <div class="uk-modal-body">
	        	<input type="text" placeholder="제목"/>
	        	<div>
	            	<textarea name="" id=""></textarea>
				</div>
	            <div class="qw-bottom">
	            	<p><input type="file"/></p>
	            	<label for="hidden-upload"><input id="hidden-upload" type="checkbox"/>비공개글 작성</label>
	            </div>
	        </div>
	        <div class="uk-modal-footer uk-text-right">
	            <button class="uk-button uk-button-primary" type="button">저장</button>
	            <button class="uk-button uk-button-default uk-modal-close" type="button">취소</button>
	        </div>
	    </div>
	</div>
</body>

<script src="../js/uikit.js"></script>
<script src="../js/uikit-icons.js"></script>
<script>
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href="/helloWorld?nowPage=${paging.nowPage}&cntPerPage="+sel;
	}
</script>
</html>
