<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.6/handlebars.min.js"></script>
<script type="text/x-handlebars-template"  id="reply-list-template" >
{{#each .}}
<div class="replyLi" >
	<div class="user-block">
		<img src="/member/getPictureById.do?id={{replyer}}" class="img-circle img-bordered-sm"/>
    </div>
	
 	<div class="timeline-item" >
  		<span class="time">
    		<i class="fa fa-clock"></i>{{prettifyDate regdate}}
	 		<a class="btn btn-primary btn-xs" id="modifyReplyBtn" data-rno={{rno}}
				style="display:{{VisibleByLoginCheck replyer}};"
	    		data-replyer={{replyer}} data-toggle="modal" data-target="#modifyModal">Modify</a>
  		</span>
	
  		<h3 class="timeline-header"><strong style="display:none;">{{rno}}</strong>{{replyer}}</h3>
  		<div class="timeline-body" id="{{rno}}-replytext">{{replytext}} </div>
	</div>
</div>
{{/each}}	
</script>
<script> //댓글 리스트
var replyPage=1;

window.onload=function(){
	getPage("<%=request.getContextPath()%>/reply/list.do?bno=${board.bno}&page="+replyPage);
}

Handlebars.registerHelper({
	"prettifyDate":function(timeValue){ //Handlbars에 날짜출력함수 등록
					var dateObj=new Date(timeValue);
					var year=dateObj.getFullYear();
					var month=dateObj.getMonth()+1;
					var date=dateObj.getDate();
					return year+"/"+month+"/"+date;
	},
	"VisibleByLoginCheck":function(replyer){
					var result="none";
					
					if(replyer == "${loginUser.id}") result="visible";
					
					return result;						  
	}
});

var printData=function(replyArr,target,templateObject){
	var template=Handlebars.compile(templateObject.html());
	var html = template(replyArr);	
	$('.replyLi').remove();
	target.after(html);
}

function getPage(pageInfo){	 
	$.getJSON(pageInfo,function(data){	
		printData(data.replyList,$('#repliesDiv'),$('#reply-list-template'));
	});
}

function replyRegist_go(){
	//alert("add reply btn");
	var replyer=$('#newReplyWriter').val();
	var replytext=$('#newReplyText').val();
	var bno=$('input[name="bno"]').val()
	
	if(!(replyer && replytext)){
		alert("작성자 혹은 내용은 필수입니다.");
		return;
	}
	
	var data={
			"bno":""+bno+"",
			"replyer":replyer,
			"replytext":replytext	
	}
	$.ajax({
		url:"/reply/regist.do",
		type:"post",
		data:JSON.stringify(data),	
		contentType:'application/json',
		success:function(data){
			var result=data.split(',');
			if(result[0]=="SUCCESS"){
				alert('댓글이 등록되었습니다.');
				replyPage=result[1]; //페이지이동
				getPage("/reply/list.do?bno="+bno+"&page="+result[1]); //리스트 출력
				$('#newReplyText').val("");				
			}else{
				alert('댓글이 등록을 실패했습니다.');	
			}
		}
	});
}
</script>











