// jQuery.ntSaveForms: author Nazar Tokar * nazarTokar.com * dedushka.org * Copyright 2014
// jQuery.Storage: author Dave Schindler * Distributed under the MIT License * Copyright 2010
// updated on 2014-03-14
 
(function(jQuery){var isLS=typeof window.localStorage!=="undefined";function wls(n,v){var c;if(typeof n==="string"&&typeof v==="string"){localStorage[n]=v;return true}else if(typeof n==="object"&&typeof v==="undefined"){for(c in n)if(n.hasOwnProperty(c))localStorage[c]=n[c];return true}return false}function wc(n,v){var dt,e,c;dt=new Date;dt.setTime(dt.getTime()+31536E6);e="; expires="+dt.toGMTString();if(typeof n==="string"&&typeof v==="string"){document.cookie=n+"="+v+e+"; path=/";return true}else if(typeof n===
"object"&&typeof v==="undefined"){for(c in n)if(n.hasOwnProperty(c))document.cookie=c+"="+n[c]+e+"; path=/";return true}return false}function rls(n){return localStorage[n]}function rc(n){var nn,ca,i,c;nn=n+"=";ca=document.cookie.split(";");for(i=0;i<ca.length;i++){c=ca[i];while(c.charAt(0)===" ")c=c.substring(1,c.length);if(c.indexOf(nn)===0)return c.substring(nn.length,c.length)}return null}function dls(n){return delete localStorage[n]}function dc(n){return wc(n,"",-1)}jQuery.extend({Storage:{set:isLS?
wls:wc,get:isLS?rls:rc,remove:isLS?dls:dc}})})(jQuery);

(function ntSaveForms() {
	var text, cl;
	$(".ntSaveForms").each(function(i) {
		cl = "ntSaveForms"+i;
		$(this).addClass(cl); // add new class
		text = $.Storage.get(cl);
		if (text && text.length > 0 && !$(this).val()) {
			$(this).val(text); // set field data
		}
	});

	$(".ntSaveForms").keyup(function() {
		$.Storage.set($(this).attr("class").split(" ")[$(this).attr("class").split(" ").length -1], $(this).val()); // save field data
	});

	$(".ntSaveFormsSubmit").click(function() {
		$(".ntSaveForms").each(function(i) {
			$.Storage.remove("ntSaveForms"+i); // remove data
		});
	});
})();