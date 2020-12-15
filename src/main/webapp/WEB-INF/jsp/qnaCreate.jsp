<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="quesrion-write-modal" uk-modal>
    <div class="uk-modal-dialog">
        <button class="uk-modal-close-default" type="button" uk-close></button>
        <div class="uk-modal-header">
            <h2 class="uk-modal-title">질문등록</h2>
        </div>
        <div class="uk-modal-body">
            <input type="text"  placeholder="    제목" v-model="newQna.qnaTitle"/>
            <div>
                <textarea placeholder="    내용을 입력하세요." v-model="newQna.qnaContents"></textarea>
            </div>
            <div class="qw-bottom">
                <v-file-input  show-size label="File input"></v-file-input>
                <p><input id="attach"  type="file"/></p>
                <label for="hidden-upload">
                    <input id="hidden-upload"  type="checkbox" v-model="newQna.qnaSecret" />비공개글 작성</label>
            </div>
        </div>
        <div class="uk-modal-footer uk-text-right">
            <button class="uk-button uk-button-primary" @click="createQna()" type="button">저장</button>
            <button class="uk-button uk-button-default uk-modal-close" type="button">취소</button>
        </div>
    </div>
</div>

