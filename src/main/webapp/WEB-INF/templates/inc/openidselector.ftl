[#ftl]
[#include "./includes.ftl"]

<form class="openid" method="post" action="[@spring.url "/jh_openid_security_check"/]"> 
	<div><ul class="providers"> 
		<li class="openid" title="OpenID"><img src="[@spring.url "/img/openidW.png"/]" alt="icon" /> 
			<span><strong>http://{your-openid-url}</strong></span></li> 
		<li class="direct" title="Google"> 
			<img src="[@spring.url "/img/googleW.png"/]" alt="icon" /><span>https://www.google.com/accounts/o8/id</span></li> 
  		<li class="direct" title="Yahoo"> 
			<img src="[@spring.url "/img/yahooW.png"/]" alt="icon" /><span>http://yahoo.com/</span></li> 
  		<li class="username" title="AOL screen name"> 
			<img src="[@spring.url "/img/aolW.png"/]" alt="icon" /><span>http://openid.aol.com/<strong>username</strong></span></li> 
  		<li class="username" title="MyOpenID user name"> 
			<img src="[@spring.url "/img/myopenid.png"/]" alt="icon" /><span>http://<strong>username</strong>.myopenid.com/</span></li> 
  		<li class="username" title="Flickr user name"> 
			<img src="[@spring.url "/img/flickr.png"/]" alt="icon" /><span>http://flickr.com/<strong>username</strong>/</span></li> 
  		<li class="username" title="Technorati user name"> 
			<img src="[@spring.url "/img/technorati.png"/]" alt="icon" /><span>http://technorati.com/people/technorati/<strong>username</strong>/</span></li> 
  		<li class="username" title="Wordpress blog name"> 
			<img src="[@spring.url "/img/wordpress.png"/]" alt="icon" /><span>http://<strong>username</strong>.wordpress.com</span></li> 
  		<li class="username" title="Blogger blog name"> 
			<img src="[@spring.url "/img/blogger.png"/]" alt="icon" /><span>http://<strong>username</strong>.blogspot.com/</span></li> 
  		<li class="username" title="LiveJournal blog name"> 
			<img src="[@spring.url "/img/livejournal.png"/]" alt="icon" /><span>http://<strong>username</strong>.livejournal.com</span></li> 
  		<li class="username" title="ClaimID user name"> 
			<img src="[@spring.url "/img/claimid.png"/]" alt="icon" /><span>http://claimid.com/<strong>username</strong></span></li> 
  		<li class="username" title="Vidoop user name"> 
			<img src="[@spring.url "/img/vidoop.png"/]" alt="icon" /><span>http://<strong>username</strong>.myvidoop.com/</span></li> 
  		<li class="username" title="Verisign user name"> 
			<img src="[@spring.url "/img/verisign.png"/]" alt="icon" /><span>http://<strong>username</strong>.pip.verisignlabs.com/</span></li> 
	</ul></div> 
  	<fieldset> 
  		<label for="openid_username">Enter your <span>Provider user name</span></label> 
  		<div>
  			<span></span>
			<input type="text" name="openid_username" />
			<span></span> 
  			<input type="submit" value="Login" />
  		</div> 
	</fieldset> 
  	<fieldset> 
  		<label for="openid_identifier">Enter your <a class="openid_logo" href="http://openid.net">OpenID</a></label> 
		<div>
			<input type="text" name="openid_identifier" /> 
			<input type="submit" value="Login" />
		</div> 
	</fieldset> 
</form>

<p><a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/"><img alt="Creative Commons License" style="border-width:0" src="http://creativecommons.org/images/public/somerights20.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/">Creative Commons Attribution-Share Alike 3.0 Unported License</a>.</p>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.1/jquery.min.js"></script>
<script type="text/javascript" src="[@spring.url "/js/jquery.openid.js"/]"></script>
<script type="text/javascript">  $(function() { $("form.openid:eq(0)").openid(); });</script>
