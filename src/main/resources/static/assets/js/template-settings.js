/**
 * ======================================
 * template settings
 * ======================================
 */

(function ($) {
  "use strict";

  // color switcher
  $(".color-trigger").on("click", function () {
    $(this).parent().toggleClass("visible-palate");
    $(this).hide();
    $(".close-color-trigger").show();
  });

  $(".close-color-trigger").on("click", function () {
    $(this).parent().toggleClass("visible-palate");
    $(this).hide();
    $(".color-trigger").show();
  });

  $(".color-palate .colors-list .palate").on("click", function () {
    console.log("Palate clicked");
    var newThemeColor = $(this).attr("data-theme-file");
    console.log("New theme color:", newThemeColor);
    var targetCSSFile = $('link[id="switch-color"]');
    console.log("Target CSS file:", targetCSSFile);
    $(targetCSSFile).attr("href", newThemeColor);
    $(".color-palate .colors-list .palate").removeClass("active");
    $(this).addClass("active");
  });

  $(".color-palate-inner").on("click", "ul li", function () {
    console.log("List item clicked");
    $(this).closest("ul").children("li").removeClass("active");
    $(this).addClass("active");
  });

  // header styles
  var layoutChangerBtn = $(".color-palate .header-version li");
  var body = $("body");
  layoutChangerBtn.on("click", function (e) {
    var $this = $(this);
    if ($this.hasClass("box")) {
      body.removeClass("want-sticky-header");
    } else {
      body.addClass("want-sticky-header");
    }
  });

  // dark version
  var layoutChangerBtn = $(".color-palate .dark-version li");
  var body = $("body");
  layoutChangerBtn.on("click", function (e) {
    var $this = $(this);
    if ($this.hasClass("box")) {
      body.addClass("dark-body");
    } else {
      body.removeClass("dark-body");
    }
  });

  // box version
  var layoutChangerBtn = $(".color-palate .box-version li");
  var body = $("body");
  layoutChangerBtn.on("click", function (e) {
    var $this = $(this);
    if ($this.hasClass("box")) {
      body.addClass("box-layout");
    } else {
      body.removeClass("box-layout");
    }
  });

  // rtl version
  var directionChanger = $(".color-palate .rtl-version li");
  var wrapper = $(".page-wrapper");
  directionChanger.on("click", function (e) {
    var $this = $(this);
    if ($this.hasClass("rtl")) {
      wrapper.addClass("rtl");
    } else {
      wrapper.removeClass("rtl");
    }
  });
})(jQuery);
