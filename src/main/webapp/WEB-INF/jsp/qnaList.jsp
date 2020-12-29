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
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
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

            <button class="write-btn" type="button" @click="qnaWriteModal">+ 글쓰기</button>

        </div>

        <div class="qna-search">
            <input type="search" placeholder="검색어입력" v-model="searchname"/>
            <button class="search-btn"><span class="uk-margin-small-right" uk-icon="search" ></span></button>
        </div>

        <ul class="qna-list" uk-accordion="multiple: true" v-for="(qna, index) in qnaModules"
            v-if="qna.qna_title.includes(searchname)||qna.qna_contents.includes(searchname)||qna.qna_name.includes(searchname)">
            <li>

                <a class="uk-accordion-title" >
                    <span class="user-profile"><img src="../img/component.png" alt=""></span>
                    &nbsp;&nbsp;{{qna.qna_title}}
                    <p>{{qna.qna_name}}</p>
                </a>
                <div class="uk-accordion-content">
                    <div v-if="qna.qna_user_id == userId " class="amend-delete">
                        <a class="amend-btn" uk-icon="pencil" @click="getEditQnaModal(qna.qna_no, $event)"></a>
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
                        <input class="reply" type="text" placeholder="답변작성" v-model="newQnareply" />
                        <button type="button" @click="postReply(qna.qna_no)">게시</button>
                    </div>
                    <div class="comment-list" v-for="(rep, index) in qna.qnaReplyDTOList">
                        <div class="comment-box" >
                            <span class="user-profile">
                                <span class="profile-img"><img src="../img/component2.png" alt=""></span><br/>
                                <span class="id-level">{{rep.qna_reply_name}}</span>
                            </span>
                            <span class="commnet-text">
                                <span>
                                    {{rep.qna_reply_contents}}
                                </span>
                            </span>
                        </div>
                        <div  class="comment-amend" v-if="rep.qna_reply_user_id == userId">
                            <a class="amend-btn" uk-icon="pencil" @click="getReplyEdit(rep.qna_reply_no)"></a>
                            <a class="delete-btn" uk-icon="trash" @click="putReplyDelete(rep.qna_reply_no)"></a>
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
    </div>
    <%@include file="qnaCreate.jsp"%>
    <%@include file="qnaReplyEdit.jsp"%>
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
            selectedModulesPosition : -1,
            qnaNo:0,
            page:1,
            size:5,
            pageCnt:1,
            cnt:0,
            newQnareply:'',
            searchname:'',
            qnaReplyNo:0,
            qnaReplyContents:''
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
                    console.log(res.data.qna);
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
                this.newQna.qnaTitle = '';
                this.newQna.qnaContents = '';
                this.newQna.qnaSecret = '';
                $('.uk-modal-title').text('질문 등록');
                $('.create').show();
                $('.edit').hide();
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

                        this.getQnaList(1);

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
                            size: this.size,
                            searchname: this.searchname
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
            getEditQnaModal(id, evt){
                evt.stopPropagation();
                this.selectedModulesPosition =id;
                console.log(id)
                console.log(this.selectedModulesPosition)
                this.newQna.qnaTitle = this.qnaModules.qnaTitle;
                this.newQna.qnaContents = this.qnaModules.qnaContents;
                axios.get(this.baseUrl+'/api/qnaEdit',{
                    params:{qna_no:id}
                }).then(res => {
                    console.log("질문불러오기 !"+res.data.qna_title);
                    $('.uk-modal-title').text('질문 수정');
                    $('.create').hide();
                    $('.edit').show();
                    var modal = UIkit.modal("#quesrion-write-modal");
                    modal.show();
                    this.newQna.qnaTitle = res.data.qna_title;
                    this.newQna.qnaContents = res.data.qna_contents;
                    console.log(res.data.qna_secret);

                    this.newQna.qnaSecret = res.data.qna_secret;

                })
            },
            postEditQna(){
                console.log("질문수정");
                if (this.isEmpty(this.newQna.qnaTitle)) {
                    UIkit.notification("Qna 제목은 필수 입니다.");
                }else if (this.isEmpty(this.newQna.qnaContents)) {
                    UIkit.notification("Qna 내용은 필수 입니다.");
                } else {
                    console.log("수정 시작")
                    axios.post(this.baseUrl+'/api/qnaUpdate',{
                        qnaTitle : this.newQna.qnaTitle,
                        qnaContents: this.newQna.qnaContents,
                        secret: this.newQna.qnaSecret,
                        courseId: this.courseId,
                        userId: this.userId,
                        qna_no : this.selectedModulesPosition
                    }).then(res => {
                        console.log("수정 성공!"+res.data);

                        var modal = UIkit.modal("#quesrion-write-modal");
                        modal.hide();

                        this.getQnaList(1);

                    })

                }
            },
            postReply(id){
                this.selectedModulesPosition = id;
                if (this.isEmpty(this.newQnareply)) {
                    UIkit.notification("댓글을 작성해주세요.");
                }else{
                    console.log("댓글게시 시작!")
                    axios.post(this.baseUrl+'/api/qnaReply',{
                        qnaReplyContents : this.newQnareply,
                        replyUserId: this.userId,
                        qnaNo : this.selectedModulesPosition
                    }).then(res => {
                        console.log("댓글게시 성공!"+res.data);
                        this.newQnareply = '';
                        this.getQnaList(1);

                    })
                }
            },
            getReplyEdit(id){
                this.selectReplyPosition = id;
                axios.get(this.baseUrl+'/api/qnaReplyEdit',{
                    params:{qna_reply_no:id}
                }).then(res => {
                    $('.uk-modal-title').text('댓글 수정');
                    var modal = UIkit.modal("#quesrion-reply-modal");
                    modal.show();
                    console.log(res.data);
                    this.qnaReplyContents = res.data.qna_reply_contents;


                })
            },
            postEditQnaReply(){
                axios.post(this.baseUrl+'/api/qnaReplyUpdate',{
                    qnaReplyContents: this.qnaReplyContents,
                    replyUserId: this.userId,
                    qnaReplyNo : this.selectReplyPosition
                }).then(res => {
                    console.log("수정 성공!"+res.data);

                    var modal = UIkit.modal("#quesrion-reply-modal");
                    modal.hide();

                    this.getQnaList(1);

                })
            },
            putReplyDelete(id){

                console.log(id);
                if(confirm('삭제하시겠습니까??')) {
                    console.log(id);
                    this.qnaReplyNo=id;
                    console.log(this.qnaReplyNo);
                    axios.put(this.baseUrl+ '/api/replyDelete', {
                        qnaReplyNo: this.qnaReplyNo
                    }).then(res => {
                        console.log(res.data);
                        this.getQnaList(1);
                    })
                        .catch(error => {
                            console.log(error.response)
                        });

                }
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
