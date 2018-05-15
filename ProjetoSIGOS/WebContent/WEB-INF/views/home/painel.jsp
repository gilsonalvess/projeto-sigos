<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">

  <!-- Header -->
  <header class="w3-container" style="padding-top:22px">
    <h5><b><i class="fa fa-dashboard"></i> Painel O.S</b></h5>
  </header>

  <div class="w3-row-padding w3-margin-bottom">
    <div class="w3-quarter">
      <div class="w3-container w3-red w3-padding-16">
        <div class="w3-left"><i class="fa fa-exclamation-triangle w3-xxlarge"></i></div>
        <div class="w3-right">
          <h3>4</h3>  
        </div>
        <div class="w3-clear"></div>
        <h4>Não iniciadas</h4>
      </div>
    </div>
 <sec:authorize access="hasRole('ROLE_ADMIN')"> 
    <div class="w3-quarter">
      <div class="w3-container w3-blue w3-padding-16">
        <div class="w3-left"><i class="fa fa-money w3-xxlarge"></i></div>
        <div class="w3-right">
          <h3>15</h3>
        </div>
        <div class="w3-clear"></div> 
        <h4>Orçamento</h4>
      </div>
    </div>
    <div class="w3-quarter">
      <div class="w3-container w3-teal w3-padding-16">
        <div class="w3-left"><i class="fa fa-tasks w3-xxlarge"></i></div>
        <div class="w3-right">
          <h3>23</h3>
        </div>
        <div class="w3-clear"></div>
        <h4>Serviço em execução</h4>
      </div>
    </div>
    <div class="w3-quarter">
      <div class="w3-container w3-green w3-text-white w3-padding-16">
        <div class="w3-left"><i class="fa fa-check w3-xxlarge"></i></div>
        <div class="w3-right">
          <h3>50</h3>
        </div>
        <div class="w3-clear"></div>
        <h4>Finalizadas</h4>
      </div>
    </div>
  </div>
</sec:authorize>
  <hr>
  <div class="w3-container">
    <h5>Estatísticas orçamentos</h5>
    
    <p>Aprovados</p>
    <div class="w3-grey">
      <div class="w3-container w3-center w3-padding w3-green" style="width:75%">+75%</div>
    </div>

    <p>Em negociação</p>
    <div class="w3-grey">
      <div class="w3-container w3-center w3-padding w3-orange" style="width:50%">50%</div>
    </div>

    <p>Reprovados</p>
    <div class="w3-grey">
      <div class="w3-container w3-center w3-padding w3-red" style="width:25%">25%</div>
    </div>
  </div>
  <hr>
  <br>
</sec:authorize>