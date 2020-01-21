$$('h4').forEach(function(h4) {
var len = h4.textContent.length, s = h4.style;
 s.width = len + 'ch';
 s.animationTimingFunction = "steps("+len+"),steps(1)";
});