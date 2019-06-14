/*
Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
For licensing, see license.txt or http://cksource.com/ckfinder/license
*/

CKFinder.customConfig = function( config )
{
	// Define changes to default configuration here.
	// For the list of available options, check:
	// http://docs.cksource.com/ckfinder_2.x_api/symbols/CKFinder.config.html

	// Sample configuration options:
	// config.uiColor = '#BDE31E';
	// config.language = 'fr';
	// config.removePlugins = 'basket';
    // 上传文件时浏览服务文件夹
    config.filebrowserBrowseUrl ='WebRoot/ckfinder/ckfinder.html';
// 上传图片时浏览服务文件夹
    config.filebrowserImageBrowseUrl ='WebRoot/ckfinder/ckfinder.html?Type=Images';
// 上传Flash时浏览服务文件夹
    config.filebrowserFlashBrowseUrl ='WebRoot/ckfinder/ckfinder.html?Type=Flash';
// 上传文件按钮(标签)
    config.filebrowserUploadUrl ='WebRoot/ckfinder/core/connector/aspx/connector.aspx?command=QuickUpload&type=Files';
// 上传图片按钮(标签)
    config.filebrowserImageUploadUrl ='WebRoot/ckfinder/core/connector/aspx/connector.aspx?command=QuickUpload&type=Images';
// 上传Flash按钮(标签)
    config.filebrowserFlashUploadUrl ='WebRoot/ckfinder/core/connector/aspx/connector.aspx?command=QuickUpload&type=Flash';
};
