var $stats;
var $wnd = window;
var $doc = $wnd.document;function Q(){}
function Y(){}
function fe(){}
function tb(){}
function Jc(){}
function gd(){}
function kd(){}
function Ad(a,b){}
function sb(){hb()}
function v(){kb(hb())}
function vd(a){kb(hb())}
function Qd(a){this.c=a}
function Wd(){this.b=new Date}
function N(){N=fe;M=new Q}
function id(){v.call(this)}
function td(){v.call(this)}
function yd(){v.call(this)}
function Yd(){v.call(this)}
function Yc(a,b){this.b=a;this.c=b}
function _c(a,b){this.b=a;this.c=b}
function bd(a,b){this.b=a;this.c=b}
function Ud(){this.b=vb(pc,ie,0,0,0)}
function J(a){$wnd.clearTimeout(a)}
function wd(a){return Math.floor(a)}
function qd(a){return a!=null&&a!=0}
function Nc(a){return new Lc[a]}
function Cd(b,a){return b.indexOf(a)}
function Sc(b,a){return b.lineWidth=a}
function Rc(b,a){return b.fillStyle=a}
function Tc(b,a){return b.strokeStyle=a}
function Qc(c,a,b){return c.moveTo(a,b)}
function Eb(a,b){return a.cM&&a.cM[b]}
function Db(a,b){return a.cM&&!!a.cM[b]}
function Fb(a,b){return a!=null&&Db(a,b)}
function Md(a,b){(a<0||a>=b)&&Nd(a,b)}
function Id(a){return vb(rc,ie,1,a,0)}
function I(a){return a.$H||(a.$H=++A)}
function U(a){return parseInt(a)||-1}
function Dd(b,a){return b.lastIndexOf(a)}
function Ed(c,a,b){return c.lastIndexOf(a,b)}
function D(a,b,c){return a.apply(b,c);var d}
function pd(a){return typeof a==ve&&a>0}
function Gd(b,a){return b.substr(a,b.length-a)}
function Gb(a){return a!=null&&a.tM!=fe&&!Db(a,1)}
function kb(){var a;a=ib(new sb);mb(a)}
function ed(){ed=fe;dd=zc((new Wd).b.getTime())}
function Ab(){Ab=fe;yb=[];zb=[];Bb(new tb,yb,zb)}
function hb(){hb=fe;Error.stackTraceLimit=128}
function Td(a,b){Md(b,a.c);return a.b[b]}
function Sd(a,b){xb(a.b,a.c++,b);return true}
function od(a){var b=Lc[a.b];a=null;return b}
function R(a,b){!a&&(a=[]);a[a.length]=b;return a}
function V(a,b){a.length>=b&&a.splice(0,b);return a}
function X(){try{null.a()}catch(a){return a}}
function tc(a){if(Fb(a,7)){return a}return new w(a)}
function w(a){v.call(this);this.b=a;jb(new sb,this)}
function jb(a,b){var c;c=lb(a,Gb(b.b)?b.b:null);mb(c)}
function yc(a,b){return a.l==b.l&&a.m==b.m&&a.h==b.h}
function vc(a,b,c){return _=new Jc,_.l=a,_.m=b,_.h=c,_}
function ld(a){return typeof a==ve?'S'+(a<0?-a:a):a}
function Nd(a,b){throw new vd('Index: '+a+', Size: '+b)}
function Pd(a){if(a.b>=a.c.c){throw new Yd}return Td(a.c,a.b++)}
function Pc(g,a,b,c,d,e,f){return g.bezierCurveTo(a,b,c,d,e,f)}
function vb(a,b,c,d,e){var f;f=ub(e,d);wb(a,b,c,f);return f}
function wb(a,b,c,d){Ab();Cb(d,yb,zb);d.cZ=a;d.cM=b;d.qI=c;return d}
function Cb(a,b,c){Ab();for(var d=0,e=b.length;d<e;++d){a[b[d]]=c[d]}}
function Bb(a,b,c){var d=0,e;for(var f in a){if(e=a[f]){b[d]=f;c[d]=e;++d}}}
function G(a,b,c){var d;d=E();try{return D(a,b,c)}finally{H(d)}}
function lb(a,b){var c;c=db(a,b);return c.length==0?(new Y).o(b):V(c,1)}
function ib(a){var b;b=V(lb(a,X()),3);b.length==0&&(b=V((new Y).k(),1));return b}
function nd(a){var b;b=new kd;we+(qd(a)?ld(a):me+I(b));pd(a)&&rd(a,b);return b}
function H(a){a&&P((N(),M));--z;if(a){if(C!=-1){J(C);C=-1}}}
function K(){return $wnd.setTimeout(function(){z!=0&&(z=0);C=-1},10)}
function wc(a){return a.l+a.m*4194304+a.h*17592186044416}
function Hb(a){return ~~Math.max(Math.min(a,2147483647),-2147483648)}
function F(b){return function(){try{return G(b,this,arguments)}catch(a){throw a}}}
function O(a){var b,c;if(a.b){c=null;do{b=a.b;a.b=null;c=S(b,c)}while(a.b);a.b=c}}
function P(a){var b,c;if(a.c){c=null;do{b=a.c;a.c=null;c=S(b,c)}while(a.c);a.c=c}}
function uc(a){var b,c,d;b=a&4194303;c=~~a>>22&4194303;d=a<0?1048575:0;return vc(b,c,d)}
function Hc(){Hc=fe;Ec=vc(4194303,4194303,524287);Fc=vc(0,0,524288);Ac(1);Ac(2);Gc=Ac(0)}
function gwtOnLoad(b,c,d,e){$moduleName=c;$moduleBase=d;if(b)try{le(sc)()}catch(a){b(c)}else{le(sc)()}}
function u(a){var b,c,d;c=vb(qc,ie,6,a.length,0);for(d=0,b=a.length;d<b;++d){if(!a[d]){throw new yd}c[d]=a[d]}}
function db(a,b){var c,d,e;e=b&&b.stack?b.stack.split('\n'):[];for(c=0,d=e.length;c<d;++c){e[c]=a.n(e[c])}return e}
function Cc(a,b){var c,d,e;c=a.l-b.l;d=a.m-b.m+(~~c>>22);e=a.h-b.h+(~~d>>22);return vc(c&4194303,d&4194303,e&1048575)}
function md(a){var b;b=new kd;we+(qd(a!=0?-a:0)?ld(a!=0?-a:0):me+I(b));pd(a!=0?-a:0)&&rd(a!=0?-a:0,b);return b}
function E(){var a;if(z!=0){a=(new Date).getTime();if(a-B>2000){B=a;C=K()}}if(z++==0){O((N(),M));return true}return false}
function Ac(a){var b,c;if(a>-129&&a<128){b=a+128;xc==null&&(xc=vb(oc,ie,2,256,0));c=xc[b];!c&&(c=xc[b]=uc(a));return c}return uc(a)}
function sc(){!!$stats&&Oc('com.google.gwt.useragent.client.UserAgentAsserter');!!$stats&&Oc('gwt.test.client.CanvasTest');Uc()}
function Hd(c){if(c.length==0||c[0]>xe&&c[c.length-1]>xe){return c}var a=c.replace(/^(\s*)/,me);var b=a.replace(/\s*$/,me);return b}
function S(b,c){var d,e,f;for(d=0,e=b.length;d<e;++d){f=b[d];try{f[1]?f[0].q()&&(c=R(c,f)):f[0].q()}catch(a){a=tc(a);if(!Fb(a,7))throw a}}return c}
function ce(a,b){var c,d;if(b>0){if((b&-b)==b){return Hb(b*de(a,31)*4.6566128730773926E-10)}do{c=de(a,31);d=c%b}while(c-d+(b-1)<0);return Hb(d)}throw new td}
function ee(){be();var a,b,c;c=ae+++(new Date).getTime();a=Hb(Math.floor(c*5.9604644775390625E-8))&16777215;b=Hb(c-a*16777216);this.b=a^1502;this.c=b^15525485}
function rd(a,b){var c;b.b=a;if(a==2){c=String.prototype}else{if(a>0){var d=od(b);if(d){c=d.prototype}else{d=Lc[a]=function(){};d.cZ=b;return}}else{return}}c.cZ=b}
function be(){be=fe;var a,b,c;$d=vb(mc,ie,-1,25,1);_d=vb(mc,ie,-1,33,1);c=1.52587890625E-5;for(a=32;a>=0;--a){_d[a]=c;c*=0.5}b=1;for(a=24;a>=0;--a){$d[a]=b;b*=0.5}}
function ub(a,b){var c=new Array(b);if(a==3){for(var d=0;d<b;++d){var e=new Object;e.l=e.m=e.h=0;c[d]=e}}else if(a>0){var e=[null,0,false][a];for(var d=0;d<b;++d){c[d]=e}}return c}
function Oc(a){return $stats({moduleName:$moduleName,sessionId:$sessionId,subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date).getTime(),type:'onModuleLoadStart',className:a})}
function Bc(a,b){var c,d;c=~~a.h>>19;d=~~b.h>>19;return c==0?d!=0||a.h>b.h||a.h==b.h&&a.m>b.m||a.h==b.h&&a.m==b.m&&a.l>=b.l:!(d==0||a.h<b.h||a.h==b.h&&a.m<b.m||a.h==b.h&&a.m==b.m&&a.l<b.l)}
function Xc(a,b){var c,d;if(Bc(Cc(zc((new Wd).b.getTime()),a.b[0]),ke)){a.b[0]=zc((new Wd).b.getTime());b.clearRect(0,0,1000,800);for(d=new Qd(a.c);d.b<d.c.c;){c=Pd(d);fd(c,b)}}return null}
function T(a){var b,c,d;d=me;a=Hd(a);b=a.indexOf(ne);c=a.indexOf('function')==0?8:0;if(b==-1){b=Cd(a,Jd(64));c=a.indexOf('function ')==0?9:0}b!=-1&&(d=Hd(a.substr(c,b-c)));return d.length>0?d:oe}
function Jd(a){var b,c;if(a>=65536){b=55296+(~~(a-65536)>>10&1023)&65535;c=56320+(a-65536&1023)&65535;return String.fromCharCode(b)+String.fromCharCode(c)}else{return String.fromCharCode(a&65535)}}
function xb(a,b,c){if(c!=null){if(a.qI>0&&!Eb(c,a.qI)){throw new id}else if(a.qI==-1&&(c.tM==fe||Db(c,1))){throw new id}else if(a.qI<-1&&!(c.tM!=fe&&!Db(c,1))&&!Eb(c,-a.qI)){throw new id}}return a[b]=c}
function Mc(a,b,c){var d=Lc[a];if(d&&!d.cZ){_=d.prototype}else{!d&&(d=Lc[a]=function(){});_=d.prototype=b<0?{}:Nc(b);_.cM=c}for(var e=3;e<arguments.length;++e){arguments[e].prototype=_}if(d.cZ){_.cZ=d.cZ;d.cZ=null}}
function Dc(a){var b,c,d;if(yc(a,(Hc(),Fc))){return -9223372036854775808}if(!Bc(a,Gc)){return -wc((b=~a.l+1&4194303,c=~a.m+(b==0?1:0)&4194303,d=~a.h+(b==0&&c==0?1:0)&1048575,vc(b,c,d)))}return a.l+a.m*4194304+a.h*17592186044416}
function de(a,b){var c,d,e,f,g,i;f=a.b*15525485+a.c*1502;i=a.c*15525485+11;c=Math.floor(i*5.9604644775390625E-8);f+=c;i-=c*16777216;f%=16777216;a.b=f;a.c=i;if(b<=24){return wd(a.b*$d[b])}else{e=a.b*(1<<b-24);g=wd(a.c*_d[b]);d=e+g;d>=2147483648&&(d-=4294967296);return d}}
function mb(a){var b,c,d,e,f,g,i,j,k;k=vb(qc,ie,6,a.length,0);for(e=0,f=k.length;e<f;++e){j=Fd(a[e],pe,0);b=-1;d='Unknown';if(j.length==2&&j[1]!=null){i=j[1];g=Dd(i,Jd(58));c=Ed(i,Jd(58),g-1);d=i.substr(0,c-0);if(g!=-1&&c!=-1){U(i.substr(c+1,g-(c+1)));b=U(Gd(i,g+1))}}k[e]=new Ad(j[0],d+qe+b)}u(k)}
function Vc(a,b,c,d,e,f){var g=document.createElement('canvas');g.setAttribute('width',c);g.setAttribute('height',d);g.style.position='absolute';g.style.left=a+ue;g.style.top=b+ue;g.style.opacity=e;document.body.appendChild(g);var i='mozRequestAnimationFrame' in window?window.mozRequestAnimationFrame:window.requestAnimationFrame;var j=g.getContext('2d');function k(){f.j(j);i(k)}
i(k);return j}
function zc(a){var b,c,d,e,f,g,i,j;if(isNaN(a)){return Hc(),Gc}if(a<-9223372036854775808){return Hc(),Fc}if(a>=9223372036854775807){return Hc(),Ec}e=false;if(a<0){e=true;a=-a}d=0;if(a>=17592186044416){d=Hb(a/17592186044416);a-=d*17592186044416}c=0;if(a>=4194304){c=Hb(a/4194304);a-=c*4194304}b=Hb(a);f=vc(b,c,d);e&&(g=~f.l+1&4194303,i=~f.m+(g==0?1:0)&4194303,j=~f.h+(g==0&&i==0?1:0)&1048575,f.l=g,f.m=i,f.h=j,undefined);return f}
function fd(a,b){var c,d,e,f,g;b.beginPath();c=new _c(a.c.b+a.e.b/2,a.c.c+a.e.c/2);f=6.283185307179586/a.g;d=Dc(Cc(zc((new Wd).b.getTime()),dd))/a.f;for(g=0;g<=a.g;++g,d+=f){e=new _c(c.b+a.e.b/2*Math.sin(d),c.c+a.e.c/2*Math.cos(d));g==0?Qc(b,e.b,e.c):Pc(b,c.b+a.e.b*Math.sin(d-3*f/4),c.c+a.e.c*Math.cos(d-3*f/4),c.b+a.e.b*Math.sin(d-f/4),c.c+a.e.c*Math.cos(d-f/4),e.b,e.c)}b.closePath();Rc(b,a.b);Tc(b,a.i);Sc(b,a.d);b.fill();b.stroke()}
function Fd(o,a,b){var c=new RegExp(a,'g');var d=[];var e=0;var f=o;var g=null;while(true){var i=c.exec(f);if(i==null||f==me||e==b-1&&b>0){d[e]=f;break}else{d[e]=f.substring(0,i.index);f=f.substring(i.index+i[0].length,f.length);c.lastIndex=0;if(g==f){d[e]=f.substring(0,1);f=f.substring(1)}g=f;e++}}if(b==0&&o.length>0){var j=d.length;while(j>0&&d[j-1]==me){--j}j<d.length&&d.splice(j,d.length-j)}var k=Id(d.length);for(var n=0;n<d.length;++n){k[n]=d[n]}return k}
function Uc(){var a,b,c,d,e;c=new ee;d=new Ud;for(a=0;a<20;++a){Sd(d,(ed(),e=new gd,e.e=new bd(125+ce(c,100),125+ce(c,100)),e.c=new _c(ce(c,1000-Hb(e.e.b)),ce(c,800-Hb(e.e.c))),e.b=se+ce(c,255)+te+ce(c,255)+te+ce(c,255)+te+(de(c,26)*1.4901161193847656E-8+de(c,27)*1.1102230246251565E-16)+re,e.i=se+ce(c,255)+te+ce(c,255)+te+ce(c,255)+te+(de(c,26)*1.4901161193847656E-8+de(c,27)*1.1102230246251565E-16)+re,e.d=ce(c,10),e.g=3+ce(c,30),e.f=1000+ce(c,3000),e))}b=wb(nc,ie,-1,[zc((new Wd).b.getTime())]);Vc(0,0,1000,800,1,new Yc(b,d))}
var me='',xe=' ',ne='(',re=')',te=',',qe='@',pe='@@',we='Class$',oe='anonymous',ve='number',ue='px',se='rgba(';var _,ke={l:30,m:0,h:0},Lc={},ie={},je={7:1};Mc(1,-1,ie);_.tM=fe;Mc(8,1,je);Mc(7,8,je);Mc(6,7,je);Mc(5,6,je,w);_.b=null;Mc(13,1,{});var z=0,A=0,B=0,C=-1;Mc(15,13,{},Q);_.b=null;_.c=null;var M;Mc(18,1,{},Y);_.k=function Z(){var a={};var b=[];var c=arguments.callee.caller.caller;while(c){var d=this.n(c.toString());b.push(d);var e=':'+d;var f=a[e];if(f){var g,i;for(g=0,i=f.length;g<i;g++){if(f[g]===c){return b}}}(f||(a[e]=[])).push(c);c=c.caller}return b};_.n=function $(a){return T(a)};_.o=function ab(a){return []};Mc(20,18,{});_.k=function eb(){return V(this.o(X()),this.p())};_.o=function fb(a){return db(this,a)};_.p=function gb(){return 2};Mc(19,20,{});_.k=function nb(){return ib(this)};_.n=function ob(a){var b,c,d,e;if(a.length==0){return oe}e=Hd(a);e.indexOf('at ')==0&&(e=Gd(e,3));c=e.indexOf('[');c!=-1&&(e=Hd(e.substr(0,c-0))+Hd(Gd(e,e.indexOf(']',c)+1)));c=e.indexOf(ne);if(c==-1){c=e.indexOf(qe);if(c==-1){d=e;e=me}else{d=Hd(Gd(e,c+1));e=Hd(e.substr(0,c-0))}}else{b=e.indexOf(re,c);d=e.substr(c+1,b-(c+1));e=Hd(e.substr(0,c-0))}c=Cd(e,Jd(46));c!=-1&&(e=Gd(e,c+1));return (e.length>0?e:oe)+pe+d};_.o=function pb(a){return lb(this,a)};_.p=function qb(){return 3};Mc(21,19,{},sb);Mc(22,1,{},tb);_.qI=0;var yb,zb;var xc=null;var Ec,Fc,Gc;Mc(31,1,{},Jc);Mc(37,1,{},Yc);_.j=function Zc(a){return Xc(this,a)};_.b=null;_.c=null;Mc(38,1,{},_c);_.b=0;_.c=0;Mc(39,1,{},bd);_.b=0;_.c=0;Mc(40,1,{},gd);_.b=null;_.c=null;_.d=0;_.e=null;_.f=0;_.g=0;_.i=null;var dd;Mc(41,6,je,id);Mc(42,1,{},kd);_.b=0;Mc(45,6,je,td);Mc(46,6,je,vd);Mc(48,6,je,yd);Mc(49,1,{},Ad);_=String.prototype;_.cM={1:1};Mc(50,1,{});Mc(51,50,{});Mc(52,1,{},Qd);_.b=0;_.c=null;Mc(53,51,{},Ud);_.c=0;Mc(54,1,{},Wd);_.b=null;Mc(55,6,je,Yd);Mc(56,1,{},ee);_.b=0;_.c=0;var $d,_d,ae=0;var le=F;var ac=nd(1),Jb=nd(9),pc=md(60),ec=nd(8),Yb=nd(7),bc=nd(6),cc=nd(49),qc=md(62),Qb=nd(31),oc=md(63),Rb=nd(32),nc=md(64),Tb=nd(38),Ub=nd(39),Vb=nd(40),Sb=nd(37),Xb=nd(42),mc=md(65),dc=nd(2),rc=md(61),Wb=nd(41),Ib=nd(5),lc=nd(56),fc=nd(50),hc=nd(51),gc=nd(52),ic=nd(53),jc=nd(54),Pb=nd(18),Ob=nd(20),Nb=nd(19),Mb=nd(21),Kb=nd(13),Lb=nd(15),Zb=nd(45),_b=nd(48),$b=nd(46),kc=nd(55);$wnd.addEventListener('load', gwtOnLoad, true);