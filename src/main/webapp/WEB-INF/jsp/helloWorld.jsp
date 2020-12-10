<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<spring:eval expression="@environment.getProperty('config.property.canvasUrl')" var="canvasUrl" />

<head>
	<meta charset="UTF-8">
	<!-- UIkit CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.5.8/dist/css/uikit.min.css" />
	<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
	<!-- UIkit JS -->
	<script src="https://cdn.jsdelivr.net/npm/uikit@3.5.8/dist/js/uikit.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/uikit@3.5.8/dist/js/uikit-icons.min.js"></script>

	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue"></script>
	<link rel="stylesheet" href="../css/uikit.css">
	<link rel="stylesheet" href="../css/uikit-rtl.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css">
	<link rel="stylesheet" href="../css/style.css">
	<title>Document</title>
</head>

<body>
	<div id="qnaEdit">
		<div class="wrapper">
			<div class="qna-top">
				<select name="" id="">
					<option value="">검색조건</option>
				</select>



				<button class="write-btn" type="button" uk-toggle="target: #quesrion-write-modal">+ 글쓰기</button>
				<button type="button" @click="test">테스틀버튼</button>
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
			</ul>

			<div class="paging">
				<ul>
					<li><a href="#" uk-icon="chevron-left"></a></li>
					<li><a class="active" href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#" uk-icon="chevron-right"></a></li>
				</ul>
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

	        	<input type="text" id="title" v-model="qnaEdit.title" placeholder="제목"/>
	        	<div>
	            	<textarea id="contents" v-model="qnaEdit.contents"></textarea>
				</div>
	            <div class="qw-bottom">
					<v-file-input v-model="files" show-size label="File input"></v-file-input>
					<p><input id="attach" v-model="qnaEdit.attachFile" type="file"/></p>
	            	<label for="hidden-upload">
						<input id="hidden-upload" v-model="qnaEdit.secret" type="checkbox"/>비공개글 작성</label>
	            </div>
	        </div>
	        <div class="uk-modal-footer uk-text-right">
	            <button class="uk-button uk-button-primary" @click="createQna()" type="button">저장</button>
	            <button class="uk-button uk-button-default uk-modal-close" type="button">취소</button>
	        </div>
	    </div>
	</div>
</body>

<script src="../js/uikit.js"></script>
<script src="../js/uikit-icons.js"></script>
<script>

	new Vue({
		el:'#quesrion-write-modal',
		data:  {
				userId:'',
				courseId: '',
				roles:'',
				title: '',
				contents: '',
				attachFile: '',
				secret: '',

		},
		methods:{
			test(){
				alert("bbbbbb");
			},
			createQna(){
				alert("aaaaa");
				console.info("changeContentType")
				if (this.isEmpty(this.qnaEdit.title)) {
					UIkit.notification("Qna 제목은 필수 입니다.");
				}if (this.isEmpty(this.qnaEdit.contents)) {
					UIkit.notification("Qna 내용은 필수 입니다.");
				} else {

					axios.post(this.baseUrl + '/qnaCreate', {
						courseId: this.courseId,
						userId: this.userId,
						qnaTitle: this.qnaEdit.title,
						qnaContents: this.qnaEdit.contents,
						attachFile: this.qnaEdit.attachFile,
						secret: this.qnaEdit.secret,
					}).then(res => {
						console.log(res.data);
						var modal = UIkit.modal("#qna-edit");
						modal.hide();
					})

				}
			}
		}
	})
</script>
</html>
