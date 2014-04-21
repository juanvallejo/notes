window.addEventListener('load',function() {
	var Admin = {
		defaults:{
			adminLoadModule:'adminContent'
		},
		load:{
			loginForm:function(callback) {
				if(document.loginForm) {
					document.loginForm.submit.addEventListener('click',function(e) {
						e.preventDefault();

						if(document.loginForm.password.value == "") {
							alert("The password cannot be empty");
						} else {
							document.loginForm.password.disabled = true;
							out.innerHTML = "Logging in, please wait...";

							var data = "password="+encodeURIComponent(document.loginForm.password.value);

							var xhr = new XMLHttpRequest();
							xhr.open('POST','login.php',true);
							xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
							xhr.send(data);
							xhr.addEventListener('readystatechange',function() {
								if(this.readyState == 4 && this.status == 200) {
									if(this.responseText == "success") {
										out.innerHTML = "Loading, please wait...";
										if(typeof callback == 'function') {
											callback.call(Admin);
										} else {
											Admin.load.uploadForm();
										}
									} else {
										document.loginForm.password.removeAttribute('disabled');
										document.loginForm.password.value = '';
										document.loginForm.password.focus();

										out.innerHTML = xhr.responseText;
									}
								}
							});
							xhr.addEventListener('error',function() {
								out.innerHTML = "Asynchronous requests not supported, defaulting to external post.";

								document.loginForm.action = "login.php";
								document.loginForm.submit();
							});
						}
					});
				} else {
					var xhr = new XMLHttpRequest();
					xhr.open('GET','admin.php?module=loginForm',true);
					xhr.send(null);
					xhr.addEventListener('readystatechange',function() {
						if(this.readyState == 4 && this.status == 200) {
							document.getElementById(Admin.defaults.adminLoadModule).innerHTML = this.responseText;
							Admin.load.loginForm();
						}
					});
				}
			},
			uploadForm:function() {
				if(document.uploadForm) {
					if(!document.getElementById("logout")) {
						var logout = document.createElement("a");
						logout.id = "logout";
						logout.innerHTML = "Logout";
						logout.style.cursor = "pointer";
						logout.addEventListener('click',function() {
							this.remove();
							Admin.session.destroy(function() {
								Admin.load.loginForm();
							});
						});
						document.getElementById("links").appendChild(logout);
					
					}
					document.getElementById("deleteAll").addEventListener('click',function() {
						if(confirm("All data will be deleted. Proceed?")) {
							var self = this;
							this.disabled = true;
							var xhr = new XMLHttpRequest();
							xhr.open('POST','admin.php',true);
							xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
							xhr.send("deleteAll=true");
							xhr.addEventListener('readystatechange',function() {
								if(this.readyState == 4 && this.status == 200) {
									self.removeAttribute('disabled');
									if(this.responseText == "success") {
										out.innerHTML = "All data deleted!";
									} else if(this.responseText == "success_norecords") {
										out.innerHTML = "No records were found to delete.";
									} else {
										out.innerHTML = this.responseText;
									}
								}
							});
						}
					});

					document.uploadForm.submit.addEventListener('click',function(e) {
						e.preventDefault();

						if(document.uploadForm.dataFile.value == "") {
							alert("The file name cannot be empty");
						} else {
							if(confirm("Upload data file\n"+document.uploadForm.dataFile.value+"?")) {
								var data = new FormData();
								data.append('upload','true');
								data.append('datafile',document.uploadForm.dataFile.files[0]);

								var xhr = new XMLHttpRequest();
								xhr.open('POST','admin.php',true);
								xhr.send(data);
								xhr.upload.addEventListener('progress',function(e) {

								});
								xhr.upload.addEventListener('load',function() {
									console.log('file upload terminated');
								});
								xhr.upload.addEventListener('error',function() {

								});
								xhr.addEventListener('readystatechange',function() {
									if(this.status == 200 && this.readyState == 4) {
										try {
											var object = JSON.parse(this.responseText);
											console.log(object);
											var con = document.getElementById('adminContent');
											con.innerHTML = '<h3>Uploading data file</h3>';
											con.innerHTML += '<p>';
											con.innerHTML += '<ul>';
											con.innerHTML += '<li>Added <b>'+object.movies.added+'</b> movies out of '+(object.movies.added+object.movies.excluded)+' movie records ('+object.movies.excluded+' failures) [Last added: '+object.movies.last_added+']</li>';
											con.innerHTML += '<li>Added <b>'+object.actors.added+'</b> actors out of '+(object.actors.added+object.actors.excluded)+' actor records ('+object.actors.excluded+' failures) [Last added: '+object.actors.last_added+']</li>';
											con.innerHTML += '<li>Added <b>'+object.directors.added+'</b> directors out of '+(object.directors.added+object.directors.excluded)+' director records ('+object.directors.excluded+' failures) [Last added: '+object.directors.last_added+']</li>';
											con.innerHTML += '<li>Added <b>'+object.directions.added+'</b> directions out of '+(object.directions.added+object.directions.excluded)+' movie / director records ('+object.directions.excluded+' failures) [Last added: '+object.directions.movie+'/'+object.directions.director+']</li>';
											con.innerHTML += '<li>Added <b>'+object.performances.added+'</b> performances out of '+object.length+' actor / movie / role records ('+object.performances.excluded+' failures) [Last added: '+object.performances.actor+'/'+object.performances.movie+'/'+object.performances.role+']</li>';
											con.innerHTML += '</ul>';
											con.innerHTML += '</p>';

											var back = document.createElement("input");
											back.type = "button";
											back.value = "Back to Administrator Menu";
											back.addEventListener('click',function() {
												Admin.load.uploadForm();
											});

											con.appendChild(back);
										} catch(e) {
											console.log(e.message);
											console.log(this.responseText);
										}
									}
								});
							}
						}
					});
				} else {
					var xhr = new XMLHttpRequest();
					xhr.open('GET','admin.php?module=uploadForm',true);
					xhr.send(null);
					xhr.addEventListener('readystatechange',function() {
						if(this.readyState == 4 && this.status == 200) {
							document.getElementById("adminTitle").innerHTML = 'Administrator Menu';
							document.getElementById(Admin.defaults.adminLoadModule).innerHTML = this.responseText;
							Admin.load.uploadForm();
						}
					});
				}
			}
		},
		init:function() {
			Admin.session.get(function(session) {
				if(session) {
					Admin.load.uploadForm();
				} else {
					Admin.load.loginForm();
				}
			});
		},
		session:{
			destroy:function(callback) {
				var xhr = new XMLHttpRequest();
				xhr.open('POST','login.php',true);
				xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
				xhr.send("session=destroy");
				xhr.addEventListener('readystatechange',function() {
					if(this.readyState == 4 && this.status == 200) {
						if(typeof callback == 'function') {
							callback.call(Admin);
						}
					}
				});
			},
			get:function(callback) {
				var xhr = new XMLHttpRequest();
				xhr.open('POST','login.php',true);
				xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
				xhr.send("session=get");
				xhr.addEventListener('readystatechange',function() {
					if(this.readyState == 4 && this.status) {
						if(typeof callback == 'function') {
							callback.call(Admin,(this.responseText == 'FALSE' ? null : this.responseText));
						}
					}
				});
			}
		}
	};

	Admin.init();
});