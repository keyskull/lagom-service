
function selectFileImage(fileObj) {
	var file = fileObj.files['0'];
	//图片方向角 added by lzk
	var Orientation = null;
	
	if (file) {
		console.log("正在上传,请稍后...");
		var rFilter = /^(image\/jpeg|image\/png)$/i; // 检查图片格式
		if (!rFilter.test(file.type)) {
			//showMyTips("请选择jpeg、png格式的图片", false);
			return;
		}
		// var URL = URL || webkitURL;
		//获取照片方向角属性，用户旋转控制
		EXIF.getData(file, function() {
		   // alert(EXIF.pretty(this));
		    EXIF.getAllTags(this); 
		    //alert(EXIF.getTag(this, 'Orientation')); 
		    Orientation = EXIF.getTag(this, 'Orientation');
		    //return;
		});
		
		var oReader = new FileReader();
		oReader.onload = function(e) {
			//var blob = URL.createObjectURL(file);
			//_compress(blob, file, basePath);
			var image = new Image();
			image.src = e.target.result;
			image.onload = function() {
				var expectWidth = this.naturalWidth;
				var expectHeight = this.naturalHeight;
				
				if (this.naturalWidth > this.naturalHeight && this.naturalWidth > 800) {
					expectWidth = 800;
					expectHeight = expectWidth * this.naturalHeight / this.naturalWidth;
				} else if (this.naturalHeight > this.naturalWidth && this.naturalHeight > 1200) {
					expectHeight = 1200;
					expectWidth = expectHeight * this.naturalWidth / this.naturalHeight;
				}
				//alert(expectWidth+','+expectHeight);
				var canvas = document.createElement("canvas");
				var ctx = canvas.getContext("2d");
				canvas.width = expectWidth;
				canvas.height = expectHeight;
				ctx.drawImage(this, 0, 0, expectWidth, expectHeight);
				//alert(canvas.width+','+canvas.height);
				
				var base64 = null;
				var mpImg = new MegaPixImage(image);
					mpImg.render(canvas, {
						maxWidth: 800,
						maxHeight: 1200,
						quality: 0.8,
						orientation: Orientation
					});
					
				base64 = canvas.toDataURL("image/jpeg", 0.8);
				
				//var img = 
				//修复ios
				if (navigator.userAgent.match(/iphone/i)) {
					console.log('iphone');
					//alert(expectWidth + ',' + expectHeight);
					//如果方向角不为1，都需要进行旋转 added by lzk
					/*if(Orientation != "" && Orientation != 1){
						alert('旋转处理');
						switch(Orientation){
						 	case 6://需要顺时针（向左）90度旋转
						 		alert('需要顺时针（向左）90度旋转');
						 		rotateImg(this,'left',canvas);
						 		break;
						 	case 8://需要逆时针（向右）90度旋转
						 		alert('需要顺时针（向右）90度旋转');
						 		rotateImg(this,'right',canvas);
						 		break;
						 	case 3://需要180度旋转
						 		alert('需要180度旋转');
								rotateImg(this,'right',canvas);//转两次
								rotateImg(this,'right',canvas);
								break;
						}		
					}*/
					
					/*var mpImg = new MegaPixImage(image);
					mpImg.render(canvas, {
						maxWidth: 800,
						maxHeight: 1200,
						quality: 0.8,
						orientation: Orientation
					});
					base64 = canvas.toDataURL("image/jpeg", 0.8);*/
				}else if (navigator.userAgent.match(/Android/i)) {// 修复android
					/*var encoder = new JPEGEncoder();
					base64 = encoder.encode(ctx.getImageData(0, 0, expectWidth, expectHeight), 80);*/
				}else{
					//alert(Orientation);
					/*if(Orientation != "" && Orientation != 1){
						//alert('旋转处理');
						switch(Orientation){
						 	case 6://需要顺时针（向左）90度旋转
						 		alert('需要顺时针（向左）90度旋转');
						 		rotateImg(this,'left',canvas);
						 		break;
						 	case 8://需要逆时针（向右）90度旋转
						 		alert('需要顺时针（向右）90度旋转');
						 		rotateImg(this,'right',canvas);
						 		break;
						 	case 3://需要180度旋转
						 		alert('需要180度旋转');
								rotateImg(this,'right',canvas);//转两次
								rotateImg(this,'right',canvas);
								break;
						}		
					}*/
					/*var mpImg = new MegaPixImage(image);
					mpImg.render(canvas, {
						maxWidth: 800,
						maxHeight: 1200,
						quality: 0.8,
						orientation: Orientation
					});
					
					base64 = canvas.toDataURL("image/jpeg", 0.8);*/
				}
				uploadImage(base64);
			};
		};
		oReader.readAsDataURL(file);
		
	}
}


//对图片旋转处理 added by lzk
function rotateImg(img, direction,canvas) {
		//alert(img);
        //最小与最大旋转方向，图片旋转4次后回到原方向  
        var min_step = 0;  
        var max_step = 3;  
        //var img = document.getElementById(pid);  
        if (img == null)return;  
        //img的高度和宽度不能在img元素隐藏后获取，否则会出错  
        /*var height = img.height;  
        var width = img.width;  */
        var height = canvas.height;  
        var width = canvas.width;  
        //alert(width+','+height);
        //var step = img.getAttribute('step');  
        var step = 2;  
        if (step == null) {  
            step = min_step;  
        }  
        if (direction == 'right') {  
            step++;  
            //旋转到原位置，即超过最大值  
            step > max_step && (step = min_step);  
        } else {  
            step--;  
            step < min_step && (step = max_step);  
        }  
        //img.setAttribute('step', step);  
        /*var canvas = document.getElementById('pic_' + pid);  
        if (canvas == null) {  
            img.style.display = 'none';  
            canvas = document.createElement('canvas');  
            canvas.setAttribute('id', 'pic_' + pid);  
            img.parentNode.appendChild(canvas);  
        }  */
        //旋转角度以弧度值为参数  
        var degree = step * 90 * Math.PI / 180;  
        var ctx = canvas.getContext('2d');  
        switch (step) {  
            case 0:  
                canvas.width = width;  
                canvas.height = height;  
                ctx.drawImage(img, 0, 0);  
                break;  
            case 1:  
                canvas.width = height;  
                canvas.height = width;  
                ctx.rotate(degree);  
                ctx.drawImage(img, 0, -height);  
                break;  
            case 2:  
                canvas.width = width;  
                canvas.height = height;  
                ctx.rotate(degree);  
                ctx.drawImage(img, -width, -height);  
                break;  
            case 3:  
                canvas.width = height;  
                canvas.height = width;  
                ctx.rotate(degree);  
                ctx.drawImage(img, -width, 0);  
                break;  
        }  
    }  

/** 记录上传数据 */
var currImgGroup = 1;
function uploadImage(imageData) {
	if (imageData) {
		// $("#show_image_" + currImgGroup).attr("src", imageData);
		$.ajax({
			type: "post",
			async: true,
			url: upload_url,
			data: imageData,
			success: function(data) {
				if (data.error != '') {
					alert(data.error);
				} else {
					alert( data);
					// $("#img_id_" + currImgGroup).val(data.imgid);
					// $("#img-show").attr("src",imgPath+data.path);
					// $("#imgId").val(data.imgid);
					// $("#imgPath").val(data.path);
					// $("#img_id_2").val(data.path);
					// $("#img_id_1").val(data.path);
					// console.log(currImgGroup);

					var html = '';
					html+=' <div class="release-picture-img icon-add "><input type="hidden" name="img_id_path" value="'+data.imgid+','+data.path+'"><i class="iconfont">&#xe604;</i> <img src="'+imgPath+data.path+'"></div>';
					// $("#image_add").append(html);
					if (typeof(ajaxUpdate)!='undefined') {
						ajaxUpdate();
					}
				}
			}
		});
	}
}