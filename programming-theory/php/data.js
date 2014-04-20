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
					var logout = document.createElement("a");
					logout.innerHTML = "Logout";
					logout.style.cursor = "pointer";
					logout.addEventListener('click',function() {
						this.remove();
						Admin.session.destroy(function() {
							Admin.load.loginForm();
						});
					});
					document.getElementById("links").appendChild(logout);

					document.uploadForm.submit.addEventListener('click',function(e) {
						e.preventDefault();

						if(document.uploadForm.dataFile.value == "") {
							alert("The file name cannot be empty");
						} else {
							if(confirm("Upload data file\n"+document.uploadForm.dataFile.value+"?")) {
								
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