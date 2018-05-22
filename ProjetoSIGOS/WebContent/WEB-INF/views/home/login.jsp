<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="w3-card w3-round w3-display-middle w3-half w3-col m3">    
   <div class="w3-container w3-round w3-theme-d4 w3-padding-16">
       <h3>SIGOS - Login</h3>
	</div>           
      <form class="w3-white" action="/ProjetoSIGOS/login" method="post">  
        <div class="w3-container">
          <br>            
          <input class="w3-input w3-border w3-round" name="username" placeholder="Usuário" required/><br>       
          <input class="w3-input w3-border w3-round" name="password" type="password" placeholder="Senha" required/><br>       
          <button class="w3-button w3-green w3-block w3-round" type="submit">Entrar</button><br>                             
        </div>                 
     </form><br>
     <div class="w3-container w3-round w3-center	 w3-light-grey">
        <p class="w3-opacity w3-small">SIGOS - Sistema Gerenciador de O.S.</p>          
    </div>
</div>