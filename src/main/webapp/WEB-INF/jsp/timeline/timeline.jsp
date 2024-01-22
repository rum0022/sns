<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="contents-box">

		<%-- 글쓰기 영역(로그인 된 사람만 보이게) --%>
		<c:if test="${not empty userId}">
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
			
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
				<%--file 태그를 숨겨두고 이미지를 클릭하면 file태그를 클릭한 것과 같은 효과 --%>
				<input type="file" id="file" accept=".jpg, .jpeg, .gif, .png" class="d-none">
				
				<%--이미지에 마우스를 올리면 마우스 커서가 변하도록 적용 --%>
					<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>
					
				<%-- 업로드 된 임시 이미지 파일 이름 나타내는 곳 --%>	
					<div id="fileName" class="ml-2"></div>
				</div>
				<button id="writeBtn" class="btn btn-info">게시</button>
			</div>
		</div> <%--// 글쓰기 영역 끝 --%>
		</c:if>
		
		<%-- 타임라인 영역 --%>
		<div class="timeline-box my-5">
			<c:forEach items="${cardViewList}" var="card">
			<%-- 카드1 --%>
			<div class="card border rounded mt-3">
				<%-- 글쓴이, 더보기(삭제) --%>
				<div class="p-2 d-flex justify-content-between">
					<span class="font-weight-bold">${card.user}</span>
					
					<a href="#" class="more-btn">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
				</div>	
				
				<%-- 카드 이미지 --%>
				<div class="card-img">
					<img src="${card.post.imagePath}" class="w-100" alt="본문 이미지">
				</div>
				
				<%-- 좋아요 --%>
				<div class="card-like m-3">
					<a href="#" class="like-btn">
						<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" width="18" height="18" alt="empty heart">
					</a>
					
					좋아요 13개
				</div>
				
				<%-- 글 --%>
				<div class="card-post m-3">
					<span class="font-weight-bold">${card.post.userId}</span>
					<span>${card.post.content}</span>
				</div>
				
				<%-- 댓글 제목 --%>
				<div class="card-comment-desc border-bottom">
					<div class="ml-3 mb-1 font-weight-bold">댓글</div>
				</div>
				
				<%-- 댓글 목록 --%>
				<div class="card-comment-list m-2">
					<%-- 댓글 내용들 --%>
					<c:forEach items="${card.commentList}" var="commentView">
							<div class="card-comment m-1">
								<span class="font-weight-bold">${commentView.user.loginId}</span>
								<span>${commentView.comment.comment}</span>
								
								<%-- 댓글 삭제 버튼 --%>
								<a href="#" class="comment-del-btn" >
									<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10" height="10">
								</a>
							</div>
						</c:forEach>	
					<%-- 댓글 쓰기 --%>
						<div class="comment-write d-flex border-top mt-2">
						<input type="text" class="form-control border-0 mr-2 comment-input" placeholder="댓글 달기"/> 
						<button type="button" class="comment-btn btn btn-light">게시</button> 
					</div>
					
				</div>--%> <%--// 댓글 목록 끝 --%>
			 </div>  <%--// 카드1 끝 --%>
		 </c:forEach>
		</div> <%--// 타임라인 영역 끝  --%>
	</div> <%--// contents-box 끝  --%>
</div>

<script>
	$(document).ready(function() {
		// 파일 이미지 클릭 => 숨겨져 있는 id="file" 동작시킨다. 
		$("#fileUploadBtn").on("click", function(e) {
			e.preventDefault(); // a태그의 기본동작을 멈춘다 (스크롤이 위로 올라가는 현상)
			$("#file").click(); // input file을 클릭한 효과
		});
		
		// 사용자가 이미지를 선택하는 순간 유효성 확인 및 업로드 된 파일명 노출
		$("#file").on("change", function(e) {
			//alert("이미지 선택");
			// 취소를 누를때 비어있는 경우 처리
			let file = e.target.files[0]; // reed-8493547_640.jpg
			if (file == null) {
				$("#file").val(""); // 파일태그 파일 제거 (보이지 않지만 업로드 될 수 있으므로 주의)
				$("#fileName").text(""); // 보여지는 파일명 비우기
				return;
			}
			
			//alert("이미지 선택");
			let fileName = e.target.files[0].name; // winter-8425500_640.jpg
			console.log(fileName);
			
			// 확장자 유효성 체크
			let ext = fileName.split(".").pop().toLowerCase(); // pop은 마지막꺼만 가져오는것
			//alert(ext);
			if (ext != "jpg" && ext != "jpeg" && ext != "gif" && ext != "png") {
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$("#file").val(""); // 파일태그 파일 제거 (보이지 않지만 업로드 될 수 있으므로 주의)
				$("#fileName").text(""); // 보여지는 파일명 비우기
				return;
			}
			
			// 유효성 통과한 이미지의 경우 파일명 노출
			$("#fileName").text(fileName);
		});
		
		// 글쓰기
		$("#writeBtn").on("click", function() {
			//alert("게시버튼 클릭");
			
			// 글내용, 이미지
			let content = $("#writeTextArea").val();
			let fileName = $("#file").val();
			if (file == '') {
				alert('파일을 업로드 해주세요');
				return;
			}
			// 이미지 확장자 체크
			
			// 파일이 업로드 된경우에만 확장자 체크 검사하고, 업로드 안됐으면 그냥 저장
			if (fileName) {
				// alert("파일이 있다.");
				//C:\fakepath\girl-8435329_640.png
				// 확장자만 뽑은 후 소문자로 변경해서 검사한다. 
				let extension = fileName.split(".").pop().toLowerCase();
				// alert(extension);
				
				if ($.inArray(extension, ['jpg', 'png', 'gif', 'jpeg']) == -1) { //확장자가 이미지가 아닐경우
					alert("이미지 파일만 업로드 할 수 있습니다.");
					$("#file").val(""); // 파일명을 비운다.
					return;
				}
			}
			// 폼태그 구성하고 이미지
			let formData = new FormData();
			formData.append("content", content);
			formData.append("file", $("#file")[0].files[0]);
			
			$.ajax({
				type:"POST"
				, url:"/post/create"
				, data:formData
				, enctype:"multipart/form-data"
				, processData:false
				, contentType:false
				, success:function(data) {
					if (data.code == 200) {
						// 글목록 화면으로 이동
						alert("글이 저장되었습니다.");
						location.reload();
					} else if (data.code == 500) {// 비로그인 일 때
						location.href = "/user/sign-in-view";
					} else {
						alert("data.error_message");
					}
				}
				, error: function(e) {
					alert("글을 저장하는데 실패 했습니다.");
				}
			
			});
		});
		
		// 댓글쓰기 (클래스로 잡기(아이디말고))
		$(".comment-btn").on("click", function() {
			//alert("댓글저장");
			let userId = $(this).data("user-id");  // 게시옆에 심어줬음 유저아이디
			// alert(userId)
			if (!userId) {
				// 비로그인이면 로그인 화면 이동
				alert("로그인을 해주세요");
				location.href = "/user/sign-in-view"
				return;
			}
			
			let postId = $(this).data("post-id");
			//alert(postId);
			
			// 댓글 내용 가져오기
			// 1)이전 태그 값 가져오기
			//let content = $(this).prev().val().trim();
			//alert(content);
			
			// 2) 형제 태그 중 input값 가져오기 (셀렉터문법)
			let content = $(this).siblings("input").val().trim();
			//alert(content);
			
			
			$.ajax({
				type:"POST"
				, url:"/comment/create"
				, data:{"postId":postId, "content":content} // userid는 안넘김
				
				, success:function(data) {
					if (data.code == 200) {
						// 글목록 화면으로 이동
						alert("글이 저장되었습니다.");
						location.reload();
					} else if (data.code == 500) {// 비로그인 일 때
						location.href = "/user/sign-in-view";
					} else {
						alert("data.error_message");
					}
				}
				, error: function(e) {
					alert("글을 저장하는데 실패 했습니다.");
				}
			});
		});
		
		// 댓글 삭제
		$(".comment-del-btn").on("click", function(e) {
			//alert("삭제");
			e.preventDefault();
			let id = $(this).data("comment-id");
			
			 // ajax
			 $.ajax({ // delete는 포스트방식으로
				type:"DELETE"
				, url:"/timeline/delete"
				, data:{"id":id}
			 
			 	, success:function(data) {
			 		if (data.code == 200) {
			 			// 성공
			 			location.reload(true); 
			 		} else if (data.code == 500) {
			 			// 실패
			 			alert(data.error_message);
			 		}
			 	}
			 	, error:function(request, status, error) {
			 		alert("삭제하는데 실패했습니다. 관리자에게 문의해주세요.");
			 	}
			 });
		});
	});
</script>