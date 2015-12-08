/*var Global = {
	init : function() {
		var header = document.createElement('div');
		header.id = 'header';
		document.body.appendChild(header);
		var outbox = document.createElement('div');
		outbox.id = 'outbox';
		document.body.appendChild(outbox);
		var footer = document.createElement('div');
		footer.id = 'footer';
		document.body.appendChild(footer);
		$('<div id="box"></div>').appendTo($('#outbox').empty());
	}
};
*/
var Global = {

		   init : function() {
		      var wrapper = document.createElement("div");
		      wrapper.id = "wrapper";
		      document.body.appendChild(wrapper);
		      
		      $("<div id='header'></div>").appendTo($("#wrapper"));
		      $("<div id='outbox'></div>").appendTo($("#wrapper"));
		         $("<div id='box'></div>").appendTo($("#outbox"));
		      $("<div id='footer'></div>").appendTo($("#wrapper"));
		   }
		};
		/*=======================================================================================================*/
		/*=======================================================================================================*/
		/*=======================================================================================================*/
		var Member = {
				join : function(project) {
					$("#box").load(project + "/member/Member.do");
				},
				login : function(project) {
					$.ajax(project + "/member/Member.do?page=login",{
						data : {
							id : $(".form-2 input:text[name=login]").val(),
							pw : $(".form-2 input:password[name=password]").val()
						},
						dataType : "json",
						success : function(data) {
							//로그인 결과가 성공이면
							if(data.result === "success"){
								$("#frm_toggle").load(project + "/global/Main.do?page=header #frm_logined");
								// 관리자 아이디로 확인되면
								if(data.admin === "yes") {
									$("#outbox").append(
										'<table id="admin_nav"><tr><td><button id="admin_home">홈</button></td></tr>'+
										'<tr><td><button id="admin_member">회원관리</button></td></tr>'+
										'<tr><td><button id="admin_movie">영화관리</button></td></tr>'+
										'<tr><td><button id="admin_statistics">통계보기</button></td></tr>'+
										'<tr><td><button id="admin_board">게시판관리</button></td></tr></table>');
									$("#admin_nav").css({
													"text-align": "center",
													"height": "450px",
													"background": "rgba(105, 4, 4, 0.41)",
													"position": "absolute",
													"right": "20px",
													"top": "40px"
									});
									$("#admin_nav a").css({
														"border":"none",
														"color":"white",
														"font-weight":"900"
														});
								}
							} else{
							//로그인 결과가 실패면
								alert("아이디 패스워드를 다시한번 확인해주세요");
							}
						},
						error : function() {
							
						}
					});
				},
				logout : function(project) {
					$.ajax(project + "/member/Member.do?page=logout",{
						dataType : "json",
						success : function(data) {
							$("#frm_toggle").empty().load(project + "/global/Main.do?page=header #frm_login");
							$("#box").load(project + "/global/Main.do?page=default");
							$("#admin_nav").remove();
						},
						error : function() {
						}
					});
				},
				
				/* 회원가입 html */
				memberJoin : function(project) {
					$('#big_wrap').html('<link rel="stylesheet" href="../css/member.css" />'
					+'<html id="main_Html"><div id="mainDiv" class="wrap"><div id="subDiv"><h3 class="inline">&nbsp;&nbsp;본인 인증</h3>'
					+'<h5 class="inline">&nbsp;&nbsp;&nbsp;안전한 서비스 이용을 위해 본인확인을 진행하고 있습니다.</h5>'
					+'<br><br><br><h4>&nbsp;&nbsp;&nbsp;본인인증시 제공되는 정보는 해당 사이트에서 직접 수령하며,인증 이외의 용도로 이용 또는 저장하지 않습니다.</h4><br><br>'
					+'<div id="imgDiv"><img src="../images/lock.png" alt="" /><br><br><img id="join" src="../images/join.PNG" alt="" /></div>'
					+'<br><br><br><br><br><br><br><br><p id ="p_f">&nbsp;&nbsp;&nbsp;입력하신 소중한 개인정보는 회원님의 명백한 동의 없이 공개 또는 제 3자에게 제공되지 않으며,자사의 보안시스템을 통해 외부위협으로부터 보호하고 있습니다.</p></div></html>'
					);
					
					$("#join").click(function(){
						history.pushState("Member_provision","","");
						Member.webEmpty();
						Member.provision(project);
					});
					
				},
				
				
				/* 약관 html 로드 */
				provision : function(project) {
					$('#big_wrap').load(project + '/member/Member.do?page=provision' )
				},
				
				
				
				/* 박스 비우기 */
				webEmpty : function() {
					$('').appendTo($('#big_wrap').empty());
				},
				
				
				
				/* 마이페이지 상세화면 => 마이페이지.jsp에서 구현*/
				
			};
				
				







		/*=======================================================================================================*/
		/*=======================================================================================================*/
		/*=======================================================================================================*/
			var Admin = {
					 	home : function(project) {
							$("#box").load(project + "/member/Member.do?page=admin_home");
						},
						member : function(project) {
							 $.getJSON(project + '/admin/Admin.do?page=member_list', function(data) {
								 var arr = [];
								 var table = '<div id="member_list"><h1>회원목록</h1>'
										+'<table id="tab_member"><tr><th>아이디</th><th>비밀번호</th>'
										+'<th>이름</th><th>생년</th><th>성별</th>'
										+'<th>전화번호</th><th>주소</th><th>이메일</th><th>등록일</th></th>';
										$.each(data, function() {
											arr.push(this.id);
											table +='<tr><td><button id="' + this.id + '" href=#>'+this.id+'</button></td><td>'+this.password+'</td>'
												+'<td>'+this.name+'</td><td>'+this.birth+'</td>'
												+'<td>'+this.gender+'</td><td>'+this.phone+'</td>'
												+'<td>'+this.addr+'</td><td>'+this.email+'</td>'
												+'<td>'+this.regdate+'</td></tr>'
										});
										table += '</table></div>';
										$(table).appendTo($('#box').empty());
										// 아이디별로 버튼 달기
										$.each(data, function(index) {
											$("#"+this.id).click(function() {
												Bom.popup(project,arr[index]);
											});
										});
										
										// css 부분
										$("#member_list").css({
																"padding-top" : "50px",
																"padding-left" : "150px",
																"background":"white",
																"height":"1000px"
																});
										$("#member_list button").css({
											"border":"none",
											"background":"none"
											
										});
										$("#tab_member").css("width","90%");
										$("#tab_member").add("#tab_member tr").add("#tab_member th").add("#tab_member td").css({
											"border" : "1px solid black",
											"border-collapse" : "collapse",
											"text-align" : "center",
										});
							});
						},
						movie : function(project) {
							 $.getJSON(project + '/admin/Admin.do?page=movie_list', function(data) {
								 var arr = [];
								 var table = '<div id="movie_list"><h1>영화목록</h1>'
								 	 	   + '<table id="tab_movie"><tr><th>영화번호</th><th>영화이름</th>'
								 	       + '<th>감독</th><th>배우</th><th>등급</th><th>런타임</th><th>가격</th>'
								 	       + '<th>장르</th><th>개봉일</th><th>종료일</th><th>줄거리</th>'
								 	       + '<th>스틸컷</th><th>트레일러</th></tr>';
									 $.each(data, function() {
										arr.push(this.filmNumber);
										table +='<tr><td><button id="'+this.filmNumber+'">'+this.filmNumber+'</button</td><td>'+this.filmName+'</td>'
											+'<td>'+this.director+'</td><td>'+this.actor+'</td>'
											+'<td>'+this.rate+'</td><td>'+this.runtime+'</td>'
											+'<td>'+this.price+'</td><td>'+this.genre+'</td>'
											+'<td>'+this.releaseDate+'</td><td>'+this.endDate+'</td>'
											+'<td>'+this.story+'</td><td><div>'+this.cut+'</div></td>'
											+'<td><div>'+this.trailer+'</div></td></tr>'
										});
										table += '</table>';
										$(table).appendTo($('#box').empty());
										// 아이디별로 버튼 달기
										$.each(data, function(index) {
											$("#"+this.filmNumber).click(function() {
												Bom2.popup(project,arr[index]);
											});
										});
										$("#movie_list div").css({
																"width":"150px",
																"overflow":"auto"
																});
										$("#movie_list").css({
											"padding-top" : "20px",
											"padding-left" : "20px",
											"padding-right" : "20px",
											"background":"white",
											"height":"1000px",
											});
										$("#movie_list button").css({
											"background":"none",
											"border":"none"
										});
										$("#tab_movie").css("width","100%");
										$("#tab_movie tr").css({"border" : "1px solid black", "border-collapse" : "collapse", "text-align" : "center"});
										$("#tab_movie th").css({"border" : "1px solid black", "border-collapse" : "collapse", "text-align" : "center"});
										$("#tab_movie td").css({"border" : "1px solid black", "border-collapse" : "collapse", "text-align" : "center"});
							});
						}
			 };
		/*=======================================================================================================*/
		/*=======================================================================================================*/
		/*=======================================================================================================*/
			 var Bom = {
				popup : function(project,id) {
					var url = project + "/admin/Admin.do?";
					var name = "팝업";
					var style = "toolbar=no, status=no, directories=no, scrollbars=yes, location=no, resizable=no, border=0, menubar=no";
					var param = "page=member_profile&id="+id;
					var width = 400;
					var height = 500;
					var xpos = (screen.availWidth - width) / 2;
					var ypos = (screen.availHeight - height) / 2;
					style = style + ",top=" + ypos + ",left=" + xpos + ",width=" + width + ",height=" + height;
					url = url + param;
					window.open(url,"",style);
					}
				};

		 /*=======================================================================================================*/
		 /*=======================================================================================================*/
		 /*=======================================================================================================*/

			 var Bom2 = {
						popup : function(project,filmNumber) {
							var url = project + "/admin/Admin.do?";
							var name = "팝업";
							var style = "toolbar=no, status=no, directories=no, scrollbars=yes, location=no, resizable=no, border=0, menubar=no";
							var param = "page=movie_profile&filmNumber="+filmNumber;
							var width = 400;
							var height = 500;
							var xpos = (screen.availWidth - width) / 2;
							var ypos = (screen.availHeight - height) / 2;
							style = style + ",top=" + ypos + ",left=" + xpos + ",width=" + width + ",height=" + height;
							url = url + param;
							window.open(url,"",style);
							}
						};
		 /*=======================================================================================================*/
		 /*=======================================================================================================*/
		 /*=======================================================================================================*/
			 var Movie = {
					 	home : function(project) {
							$("#box").load(project + "/movie/Movie.do");
						},
						ranking : function(project) {
							var arr = [];
							$.getJSON(project + '/movie/Movie.do?page=movie_Chart', function(data) {
								var rank = '<div id="test"><h2 style="color: white; padding-top: 10;">무비차트</h2></div>';
								$.each(data, function(index, value) {
									rank += '<div class="chart_rank" id="chart_rank'+index+'"><div class="chart_ranking chart_font_17 chart_bold">'+'NO.'+(index+1)+'</div>'
											+'<img id='+this.filmNumber+' src="../images/'+this.filmNumber+'.jpg" alt="" width="250" height="350"><div class="chart_desc chart_bold">'+this.filmName+'</div></div>';
									arr.push(this.filmNumber);
								});
												
							$('#movie_wrap').empty().append(rank);
							$.each(data, function(i, val) {
								$('#'+arr[i]).click(function() {
									
										$.ajax(project + '/movie/Movie.do?page=movie_name', {
											data : 	{
												filmNumber :arr[i],
											},
											dataType : 'json',
											success : function(data) {
											
												$('#movie_wrap').empty();
											 	Movie.movieName(project,data);
											},
											error : function(xhr, status, msg) {
												alert('에러발생 : '+ status+', 내용 : '+msg );
											}
										});
									
							});

							});
							});

						},
						movieName : function(project,data) {
				 			$.getJSON(project + '/movie/movie_name/'+data.filmNumber, 
									function(data) {
										var movieInfom = '<h1>무비페이지</h1>'
											+'<div id="movie_info"><div id="movie_poster"><img id="movie_float" src="../images/'+data.filmNumber+'.jpg;" alt="" width="250" height="350" /></div>'
											+'<h2>'+data.filmName+'</h2>'
											+'<table id="movie_tab" style= "border : 1px solid black"><tr><th style="color: grey; font-size: 18px">예매율</th><td>10.5%</td></tr><tr><th>감독</th>'
											+'<td>'+data.director+'</td><tr><th>배우</th><td>'+data.actor+'</td></tr>'
											+'<tr><th>장르</th><td>'+data.genre+'</td></tr><tr><th>기본</th><td>'+data.rate+', '+data.runtime+', '+data.country+'분 '+'</td></tr><tr><th>개봉</th>'
											+'<td>'+data.releaseDate+' <a href="../ticket/ticket.html"><input type="button" value="바로 예매" class="movie_font_20 movie_bold movie_bg_color_green movie_txt_color_white "></a></td></tr></table>'
											+'<div class="movie_margin_auto"></div><br /><div class="movie_infonavi movie_font_20"><ul><li><a href="#movie_story">영화스토리</a></li>'
											+'<li><a href="#movie_tra">트레일러</a></li><li><a href="#movie_cut">스틸컷</a></li><li><a href="#movie_review">리뷰</a></li></ul></div></div>'
											+'<div id="movie_story" class="movie_story_lay movie_margin_b20"><h2>영화 스토리</h2><div class="movie_story movie_margin_a10"><img src="../images/'+data.story+'.JPG;" alt="" /></div></div>'
											+'<div id="movie_cut" class="movie_cut_lay movie_margin_b20"><h2>스틸컷<input type="button" value="스틸컷 더보기" id="cutmore" class="movie_bold movie_bg_color_green movie_txt_color_white "></h2>'
											+'<div class="movie_cut movie_margin_l30  movie_float"><a href="#"><img src="../images/'+data.filmNumber+'1.jpg;" alt="" width="250" height="161" /></a></div>'
											+'<div class="movie_cut movie_margin_l20 movie_float"><a href="#"><img src="../images/'+data.filmNumber+'2.jpg;" alt="" width="250" height="161" /></a></div>'
											+'<div class="movie_cut movie_margin_l20 movie_float"><a href="#"><img src="../images/'+data.filmNumber+'3.jpg;" alt="" width="250" height="161" /></a></div>'
											+'<div class="movie_cut movie_margin_l20 movie_float"><a href="#"><img src="../images/'+data.filmNumber+'4.jpg;" alt="" width="250" height="161" /></a></div></div>'
											
										$('#movie_wrap').html(movieInfom).append(Movie.trailer(project,data));
										$('#movie_info').css('border', '1px solid black').css('width', '95%').css('height', '500px').css('margin','20px');
										$('#movie_poster').css('float', 'left').css('border', '1px solid black').css('width', '250px').css('height', '350px').css('margin', 'auto').css('margin-right','30px');
										$('#movie_float').css('float', 'left');
										$('#movie_tab').css('border', '1px solid black').css('width', '400px').css('height', '300px').css('border-collapse', 'collapse');
										$('#cutmore').click(function() {
											Movie.movieBasic(project,data);
										});
									});
						},
						trailer : function(project,data) {
							$.getJSON(project + '/movie/Movie.do?page=movie_Tra&filmNumber='+data.filmNumber, function(data) {
								var movieTra = '<div id="movie_tra" class="movie_tra_lay movie_margin_b20 "><h2>트레일러</h2>';
								$.each(data, function(index, value) {
									movieTra += '<div class="movie_tra margin_l20 movie_float"><iframe width="350" height="200" src="https://www.youtube.com/embed/'+value+'" frameborder="0" allowfullscreen></iframe></div>';
								});
									movieTra += '<div class="movie_tra_name movie_float movie_margin_r35"><strong>(1차 예고편)</strong></div><div class="movie_tra_name movie_float movie_margin_r30"><strong>(2차 예고편)</strong></div>'
												+'<div class="movie_tra_name movie_float"><strong>(3차 예고편)</strong></div></div><div id="movie_cut" class="movie_cut_lay movie_margin_b20">'
												+'<div id="movie_review" class="movie_review_lay"><h2>리뷰</h2></div>'
												+'<div class="movie_infonavi movie_font_20"><ul><li><a href="#movie_info">영화상세</a></li><li><a href="#movie_story">영화스토리</a></li>'
												+'<li><a href="#movie_tra">트레일러</a></li><li><a href="#movie_cut">스틸컷</a></li></ul></div></div>';
												
									return movieTra;
							});
						},
						cutMore : function(project,data) {
							
							$.getJSON(project + '/movie/Movie.do?page=movie_Cut&filmNumber='+data.filmNumber, function(data) {
								var movieCut = '<div class="cut_allcut_lay">';
								$.each(data, function(index, value) {
									movieCut += '<div class="cut_allcut cut_margin_l30 cut_margin_t20 cut_margin_b20  cut_float"><a href="../images/'+value+'.jpg;">'
										+'<img src="../images/'+value+'.jpg;" alt="" width="250" height="161" /></a></div>';
										
								});
								movieCut+='</div>' ;
								$("#movie_wrap").append(movieCut);
							});
						},
					
						movieBasic : function(project,data) {
							$.getJSON(project + '/movie/Movie.do?page=movie_Basic&filmNumber='+data.filmNumber, function(data) {
								    var movieBasic ='<div><h1>영화상세<input type="button" value="영화정보" id="movie_home" class="cut_bold cut_bg_color_orange cut_txt_color_white "></h1></div>'
									+'<div id="cut_info" class="cut_info cut_margin_b20"><div class="cut_poster cut_margin_r30"><img class="float" src="../images/'+data.filmNumber+'.jpg;" alt="" width="250" height="350" /></div>'
									+'<h2>'+data.filmName+'</h2><table style="border: solid 1px white; width: 400; height: 300px"><tr><th style="color: grey; font-size: 18px">예매율</th>'
									+'<td>10.5%</td></tr><tr><th>감독</th><td>'+data.director+'</td><tr><th>배우</th><td>'+data.actor+'</td></tr>'
									+'<tr><th>장르</th><td>'+data.genre+'</td></tr><tr><th>기본</th><td>'+data.rate+', '+data.runtime+'분 '+', '+data.country+'</td></tr>'
									+'<tr><th>개봉</th><td>'+data.releaseDate+'<a href="../ticket/ticket.html"><input type="button" value="바로 예매" class="cut_font_20 cut_bold cut_bg_color_green cut_txt_color_white "></a></td></tr></table></div>';

								$('#movie_wrap').html(movieBasic);
								Movie.cutMore(project,data);
								$('#movie_home').click(function() {
									$('#movie_wrap').empty();
									Movie.movieName(project,data);
								});
							});
					
						}
			 };
			 
		 /*=======================================================================================================*/
		 /*=======================================================================================================*/
		 /*=======================================================================================================*/
			 var Ticket = {
						movie : null,
						theater : null,
						date : null,
						time : null,
						$movieratelist : null,
						$movieasclist : null,
						$theaterlist : null,
						$datelist : null,
						
						ticket_sub : function(project) {
							/* alert(this.movie+", "+this.theater+", "+this.date); */
							$.ajax(project + '/ticket/Ticket.do',{
								type : 'get',
								data : {
									movie : $("input:radio[name=movie]:checked").val(),
									theater : $("input:radio[name=theater]:checked").val(),
									date : $("input:radio[name=date]:checked").val(),
									page : "movieSelect"
								},
								async : true,
								dataType : 'json',
								success : function(data) {
									var $tl = null;
									var $dl = null;
									var $timel = null;
									$.each(data, function(index,val) {
										if (index===0) {
										$tl = val;
										} else if (index===1) {
										$dl = val;
										} else if (index===2) {
										$timel = val;
										}
									});
									/* alert("$dl"+$dl); */
									if (Ticket.theater===null || Ticket.$theaterlist!==$tl) {
										/* alert("기존"+Ticket.movie+"현재선택"+$("input:radio[name=movie]:checked").val()); */
										var theater_list = 
											'<div class="ticket_list-category"><dl>';
											$.each(data, function(index,val) {
												if (index===0) {
													Ticket.$theaterlist = val;
												$.each(val, function() {
													theater_list += '<dt><input type="radio" name="theater" value="'+this+'"/>'+this+'</dt>';
												});
												}
											});
											theater_list += '</dl></div>';
											$(theater_list).appendTo($('#theater_list').empty());
									}
									if (Ticket.date===null || Ticket.$datelist!==$dl) {
										var date_list = 
											'<div class="ticket_list-category"><dl>';
											$.each(data, function(index,val) {
												if (index===1) {
													Ticket.$datelist = val;
												$.each(val, function() {
													date_list += '<dt><input type="radio" name="date" value="'+this+'"/>'+this+'</dt>';
												});
												}
											});
											date_list += '</dl></div>';
											$(date_list).appendTo($('#date_list').empty());
									}
									if (Ticket.movie!=null&&Ticket.theater!=null&&Ticket.date!=null) {
										var times_list = 
											'<div class="ticket_list-category"><dl>';
											$.each(data, function(index,val) {
												if (index===2) {
												$.each(val, function() {
													times_list += '<dt><input type="radio" name="time" value="'+this+'"/>'+this+'</dt>';
												});
												}
											});
											times_list += '</dl></div>';
											$(times_list).appendTo($('#times_list').empty());
									}
								},
								error : function(xhr, status, msg) {
									alert('에러발생상테 : '+status+',내용:'+msg);
								}
							});
							this.movie=$("input:radio[name=movie]:checked").val();
							/* alert(this.movie+", "+this.theater+", "+this.date); */
						},
					theater_list: function(project) {
						/* alert(this.movie+", "+this.theater+", "+this.date); */
						$.ajax(project + '/ticket/Ticket.do',{
							type : 'get',
							data : {
								movie : $("input:radio[name=movie]:checked").val(),
								theater : $("input:radio[name=theater]:checked").val(),
								date : $("input:radio[name=date]:checked").val(),
								page : "theaterSelect"
							},
							async : true,
							dataType : 'json',
							success : function(data) {
								var $mrl = null;
								var $mal = null;
								var $dl = null;
								var $timel = null;
								$.each(data, function(index,val) {
									if (index===0) {
										$mrl = val;
									} else if (index===1) {
										$mal = val;
									} else if (index===2) {
										$dl = val;
									} else if (index===3) {
										$timel = val;
									}
								});
								if (Ticket.movie===null) {
									var movie_rate_list = 
										'<div class="ticket_list-category"><dl>';
										$.each(data, function(index,val) {
											if (index===0) {
												Ticket.$movieratelist = val;
											$.each(val, function() {
											movie_rate_list += '<dt><input type="radio" name="movie" value="'+this+'"/>'+this+'</dt>';
											});
											}
										});
										movie_rate_list += '</dl></div>';
										$(movie_rate_list).appendTo($('#tab1').empty());
									var movie_asc_list = 
										'<div class="ticket_list-category"><dl>';
										$.each(data, function(index,val) {
											if (index===1) {
												Ticket.$movieasclist = val;
											$.each(val, function() {
												movie_asc_list += '<dt><input type="radio" name="movie" value="'+this+'"/>'+this+'</dt>';
											});
											}
										});
										movie_asc_list += '</dl></div>';
										$(movie_asc_list).appendTo($('#tab2').empty());
								}
								if (Ticket.date===null) {
								var date_list = 
									'<div class="ticket_list-category"><dl>';
									$.each(data, function(index,val) {
										if (index===2) {
											Ticket.$datelist = val;
										$.each(val, function() {
											date_list += '<dt><input type="radio" name="date" value="'+this+'"/>'+this+'</dt>';
										});
										}
									});
									date_list += '</dl></div>';
									$(date_list).appendTo($('#date_list').empty());
								}
								if (Ticket.movie!=null&&Ticket.theater!=null&&Ticket.date!=null) {
									var times_list = 
										'<div class="ticket_list-category"><dl>';
										$.each(data, function(index,val) {
											if (index===3) {
											$.each(val, function() {
												times_list += '<dt><input type="radio" name="time" value="'+this+'"/>'+this+'</dt>';
											});
											}
										});
										times_list += '</dl></div>';
										$(times_list).appendTo($('#times_list').empty());
								}
							},
							error : function(xhr, status, msg) {
								alert('에러발생상테 : '+status+',내용:'+msg);
							}
						});
						this.theater=$("input:radio[name=theater]:checked").val();
						/* alert(this.movie+", "+this.theater+", "+this.date); */
					},
					date_list : function(project) {
						
						/* alert(this.movie+", "+this.theater+", "+this.date); */
						$.ajax(project + '/ticket/Ticket.do',{
							type : 'get',
							data : {
								movie : $("input:radio[name=movie]:checked").val(),
								theater : $("input:radio[name=theater]:checked").val(),
								date : $("input:radio[name=date]:checked").val(),
								page : "dateSelect"
							},
							async : true,
							dataType : 'json',
							success : function(data) {
								var $mrl = null;
								var $mal = null;
								var $tl = null;
								var $timel = null;
								$.each(data, function(index,val) {
									if (index===0) {
										$mrl = val;
									} else if (index===1) {
										$mal = val;
									} else if (index===2) {
										$tl = val;
									} else if (index===3) {
										$timel = val;
									}
								});
								if (Ticket.movie===null) {
									var movie_rate_list = 
										'<div class="ticket_list-category"><dl>';
										$.each(data, function(index,val) {
											if (index===0) {
												Ticket.$movieratelist = val;
											$.each(val, function() {
											movie_rate_list += '<dt><input type="radio" name="movie" value="'+this+'"/>'+this+'</dt>';
											});
											}
										});
										movie_rate_list += '</dl></div>';
										$(movie_rate_list).appendTo($('#tab1').empty());
									var movie_asc_list = 
										'<div class="ticket_list-category"><dl>';
										$.each(data, function(index,val) {
											if (index===1) {
												Ticket.$movieasclist = val;
											$.each(val, function() {
												movie_asc_list += '<dt><input type="radio" name="movie" value="'+this+'"/>'+this+'</dt>';
											});
											}
										});
										movie_asc_list += '</dl></div>';
										$(movie_asc_list).appendTo($('#tab2').empty());
								}
								if (Ticket.theater===null) {
								var theater_list = 
									'<div class="ticket_list-category"><dl>';
									$.each(data, function(index,val) {
										if (index===2) {
											Ticket.$theaterlist = val;
										$.each(val, function() {
											theater_list += '<dt><input type="radio" name="theater" value="'+this+'"/>'+this+'</dt>';
										});
										}
									});
									theater_list += '</dl></div>';
									$(theater_list).appendTo($('#theater_list').empty());
								}
								if (Ticket.movie!=null&&Ticket.theater!=null&&Ticket.date!=null) {
									var times_list = 
										'<div class="ticket_list-category"><dl>';
										$.each(data, function(index,val) {
											if (index===3) {
											$.each(val, function() {
												times_list += '<dt><input type="radio" name="time" value="'+this+'"/>'+this+'</dt>';
											});
											}
										});
										times_list += '</dl></div>';
										$(times_list).appendTo($('#times_list').empty());
								}
							},
							error : function(xhr, status, msg) {
								alert('에러발생상테 : '+status+',내용:'+msg);
							}
						});
						this.date=$("input:radio[name=date]:checked").val();
						/* alert(this.movie+", "+this.theater+", "+this.date); */
					},
					ticket_choiceseat : function(project) {
						$.ajax(project + '/ticket/Ticket.do',{
							type : 'get',
							data : {
								movie : $("input:radio[name=movie]:checked").val(),
								theater : $("input:radio[name=theater]:checked").val(),
								date : $("input:radio[name=date]:checked").val(),
								time : $("input:radio[name=time]:checked").val(),
								page : "choiceseat"
							},
							async : true,
							dataType : 'json',
							success : function(data) {
								location.href=project + "/ticket/Ticket.do?page=Seats";
							},
							error : function(xhr, status, msg) {
								alert('에러발생상테 : '+status+',내용:'+msg);
							}
						});
					},
					
					
					initList : function(project) {
						$.getJSON(project + '/ticket/Ticket.do?page=initList', function(data) {
							var movie_rate_list = 
								'<div class="ticket_list-category"><dl>';
								$.each(data, function(index,val) {
									if (index===0) {
									$.each(val, function() {
									movie_rate_list += '<dt><input type="radio" name="movie" value="'+this+'"/>'+this+'</dt>';
									});
									}
								});
								movie_rate_list += '</dl></div>';
								$(movie_rate_list).appendTo($('#tab1').empty());
							var movie_asc_list = 
								'<div class="ticket_list-category"><dl>';
								$.each(data, function(index,val) {
									if (index===1) {
									$.each(val, function() {
										movie_asc_list += '<dt><input type="radio" name="movie" value="'+this+'"/>'+this+'</dt>';
									});
									}
								});
								movie_asc_list += '</dl></div>';
								$(movie_asc_list).appendTo($('#tab2').empty());
							var theater_list = 
								'<div class="ticket_list-category"><dl>';
								$.each(data, function(index,val) {
									if (index===2) {
									$.each(val, function() {
										theater_list += '<dt><input type="radio" name="theater" value="'+this+'"/>'+this+'</dt>';
									});
									}
								});
								theater_list += '</dl></div>';
								$(theater_list).appendTo($('#theater_list').empty());
								
							var date_list = 
								'<div class="ticket_list-category"><dl>';
								$.each(data, function(index,val) {
									if (index===3) {
									$.each(val, function() {
										date_list += '<dt><input type="radio" name="date" value="'+this+'"/>'+this+'</dt>';
									});
									}
								});
								date_list += '</dl></div>';
								$(date_list).appendTo($('#date_list').empty());
							var times_list = '';
							$(times_list).appendTo($('#times_list').empty());
						});
					}
				};