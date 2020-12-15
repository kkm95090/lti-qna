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
<div id="QnA">
    <div class="wrapper">
        <div class="qna-top">
            <select name="" id="search" >
                <option value="">검색조건</option>
            </select>

            <button class="write-btn" type="button" @click="qnaWriteModal">+ 글쓰기</button>

        </div>

        <div class="qna-search">
            <input type="search" placeholder="검색어입력" />
            <button class="search-btn"><span class="uk-margin-small-right" uk-icon="search"></span></button>
        </div>

        <ul class="qna-list" uk-accordion="multiple: true" v-for="(qna, index) in qnaModules">
            <li>

                <a class="uk-accordion-title" href="#">
                    <span class="user-profile"><img src="../img/component.png" alt=""></span>
                    &nbsp;&nbsp;{{qna.qna_title}}
                    <p>{{qna.qna_name}}</p>
                </a>
                <div class="uk-accordion-content">
                    <div v-if="qna.qna_user_id == userId " class="amend-delete">
                        <a class="amend-btn" uk-icon="pencil" @click="getEdit(qna.qna_no)"></a>
                        <a class="delete-btn" uk-icon="trash" @click="putQnaDelete(qna.qna_no)"></a>
                    </div>
                    <div class="question-content">
                        <p class="qna-cont">
                            {{qna.qna_contents}}
                        </p>

<%--                        <div class="file" v-if="qnaModules.attache">--%>
<%--                            <a href="#"><span uk-icon="folder"></span> 화면캡쳐.jpg</a>--%>
<%--                        </div>--%>
                    </div>
                    <div class="comment-wrap">
                        <input type="text" placeholder="답변작성" />
                        <button>게시</button>
                    </div>
                    <div class="comment-list">
                        <div class="comment-box">

									<span class="user-profile">
										<span class="profile-img"><img src="../img/component2.png" alt=""></span><br/>
										<span class="id-level">홍길동 교수(리플 작성자)</span>
									</span>

                            <span class="commnet-text">
										<span>
											공지사항에 링크있습니다. 확인해보세요<br/>
											아안 안아 낭 ㅏㄴㅇ ㅏㄴ아 나아
										</span>
									</span>
                        </div>
                        <div  class="comment-amend">
                            <a class="amend-btn" uk-icon="pencil" href="#"></a>
                            <a class="delete-btn" uk-icon="trash" href="#"></a>
                        </div>
                    </div>
                </div>
            </li>
        </ul>

        <ul class="uk-pagination uk-flex-center" uk-margin>
            <li>
                <a type="button" v-on:click="getQnaList(page-1)"><span uk-pagination-previous></span></a>
            </li>
            <li v-for=" n in parseInt(pageCnt)" :class="{ 'uk-active' : (n == page) }">
                <a type="button" v-on:click="getQnaList(n)">{{n}}</a>
            </li>
            <li>
                <a type="button" v-on:click="getQnaList(page+1)"><span uk-pagination-next></span></a>
            </li>
        </ul>
        <ul>
            <li>
                <input type="text" v-model="courseId">
                <input type="text" v-model="userId">
                <input type="text" v-model="roleName">
            </li>
        </ul>
    </div>
    <%@include file="qnaCreate.jsp"%>
</div>

</body>
<script src="../js/uikit.js"></script>
<script src="../js/uikit-icons.js"></script>
<script>
    var qnalist = new Vue({
        el:'#QnA',
        data:{
            baseUrl:'${baseUrl}',
            courseId:'${courseId}',
            userId:'${userId}',
            roleName:'${roleName}',
            qnaModules:[],
            newQna:{
                qnaTitle:'',
                qnaContents: '',
                qnaSecret:'',
            },
            qnaNo:0,
            page:1,
            size:5,
            pageCnt:1,
            cnt:0

        },
        created(){
          this.qnaList();
        },
        methods:{
            qnaList(){

                axios.get(this.baseUrl + '/api/qnaListAll', {
                    params:{
                        page:this.page,
                        size:this.size
                    }
                }).then(res => {
                    this.cnt = res.data.cnt
                    this.qnaModules = res.data.qna;
                    if (this.cnt > 0) {
                        this.pageCnt = this.cnt / this.size;
                        if ((this.cnt % this.size) > 0) {
                            this.pageCnt += 1
                        }

                    } else {
                        this.page = 1;
                        this.pageCnt = 1;
                        this.cnt = 0
                    }
                })

            },
            qnaWriteModal(){
               console.log("글쓰기 modal");
                var modal = UIkit.modal("#quesrion-write-modal");
                modal.show();
            },
            createQna(){
                console.log("질문등록");
                if (this.isEmpty(this.newQna.qnaTitle)) {
                    UIkit.notification("Qna 제목은 필수 입니다.");
                }else if (this.isEmpty(this.newQna.qnaContents)) {
                    UIkit.notification("Qna 내용은 필수 입니다.");
                } else {
                    console.log("등록 시작")
                    axios.post(this.baseUrl+'/api/qnaCreate',{
                        qnaTitle : this.newQna.qnaTitle,
                        qnaContents: this.newQna.qnaContents,
                        secret: this.newQna.qnaSecret,
                        courseId: this.courseId,
                        userId: this.userId
                    }).then(res => {
                        console.log("등록 성공!"+res.data);

                        var modal = UIkit.modal("#quesrion-write-modal");
                        modal.hide();

                        this.qnaList();

                    })

                }
            },
            getQnaList(p){
                if (p == -1) {
                    p = 1;
                }
                if ( 0 < p && p <= this.pageCnt) {
                    this.page = p;
                    axios.get(this.baseUrl + '/api/qnaListAll', {
                        params:{
                            page: this.page,
                            size: this.size
                        }
                    }).then(res => {
                        this.qnaModules = res.data.qna;
                        this.cnt = res.data.cnt;
                        if (this.cnt > 0) {
                            this.pageCnt = this.cnt / this.size;
                            if ((this.cnt % this.size) > 0) {
                                this.pageCnt += 1
                            }

                        } else {
                            this.page = 1;
                            this.pageCnt = 1;
                            this.cnt = 0
                        }

                    })
                }
            },
            putQnaDelete(id){
                if(confirm('삭제하시겠습니까??')) {
                    console.log(id);
                    this.qnaNo=id;
                    console.log(this.qnaNo);
                    axios.put(this.baseUrl+ '/api/qnaDelete', {
                            qnaNo: this.qnaNo
                    }).then(res => {
                        console.log(res.data);
                        this.goQnaList();
                    })
                    .catch(error => {
                        console.log(error.response)
                    });

                }

            },
            goQnaList(){
                var linkPath = this.canvasUrl + "/courses/" + this.selectedCourseId+"/external_tools/"+this.ltiId;
                top.location= linkPath;
            },

            isEmpty(value){
                // debug('value', value)
                // debug('typeof value', typeof value)
                // debug('Object.keys(value).length', value && Object.keys(value).length)
                // debug('Object.getOwnPropertyNames()', value && Object.getOwnPropertyNames(value))
                // debug('value.constructor.name', value && value.constructor && value.constructor.name)

                if (value === null) return true
                if (typeof value === 'undefined') return true
                if (typeof value === 'string' && value === '') return true
                if (Array.isArray(value) && value.length < 1) return true
                if (typeof value === 'object' && value.constructor.name === 'Object' && Object.keys(value).length < 1 && Object.getOwnPropertyNames(value) < 1) return true

                if (typeof value === 'object' && value.constructor.name === 'String' && Object.keys(value).length < 1) return true // new String()

                // debug('isEmpty false')
                return false
            },

        }
    })

</script>
</html>
