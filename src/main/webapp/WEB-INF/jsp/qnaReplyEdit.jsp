<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="quesrion-reply-modal" uk-modal>
    <div class="uk-modal-dialog">
        <button class="uk-modal-close-default" type="button" uk-close></button>
        <div class="uk-modal-header">
            <h2 class="uk-modal-title" id="modal-title"></h2>
        </div>
        <div class="uk-modal-body">
            <div>
                <textarea placeholder="  댓글 내용을 입력하세요." v-model="qnaReplyContents" ></textarea>
            </div>
        </div>
        <div class="uk-modal-footer uk-text-right">
            <button class="uk-button uk-button-primary" @click="postEditQnaReply()" type="button">저장</button>
            <button class="uk-button uk-button-default uk-modal-close" type="button">취소</button>
        </div>
    </div>
</div>

