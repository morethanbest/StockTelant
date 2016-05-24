<%@page import="vo.ConcreteInfoVO"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@page import="org.springframework.context.ApplicationContext" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@page import="vo.*" %>
<%@page import="controller.*" %>
<%@page import="net.sf.json.JSONArray"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Stock</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Gretong Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!-- Graph CSS -->
<link href="css/font-awesome.css" rel="stylesheet">

<link href='http://fonts.useso.com/css?family=Roboto:700,500,300,100italic,100,400' rel='stylesheet' type='text/css'/>
<link href='http://fonts.useso.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<!-- jQuery -->
<script src="js/jquery-2.2.3.min.js"></script>

<link rel="stylesheet" href="css/table1.css" media="screen" title="no title" charset="utf-8">

  <link rel="stylesheet" href="amcharts/style.css" type="text/css">
<!-- Chart -->

<script src="amcharts/amcharts.js" type="text/javascript"></script>
<script src="amcharts/serial.js" type="text/javascript"></script>
<script src="amcharts/amstock.js" type="text/javascript"></script>
<%
ApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
JSONArray kjs = (JSONArray)session.getAttribute("k");
%>
<script>
  AmCharts.ready(function () {

    createStockChart();
  });

  var chartData = <%=kjs%>;


  function createStockChart() {
    var chart = new AmCharts.AmStockChart();


    // DATASET //////////////////////////////////////////
    var dataSet = new AmCharts.DataSet();
    dataSet.fieldMappings = [{
      fromField: "open",
      toField: "open"
    }, {
      fromField: "close",
      toField: "close"
    }, {
      fromField: "high",
      toField: "high"
    }, {
      fromField: "low",
      toField: "low"
    }, {
      fromField: "volume",
      toField: "volume"
    }, {
      fromField: "value",
      toField: "value"
    }];
    dataSet.color = "#7f8da9";
    dataSet.dataProvider = chartData;
    dataSet.title = "West Stock";
    dataSet.categoryField = "date";

    var dataSet2 = new AmCharts.DataSet();
    dataSet2.fieldMappings = [{
      fromField: "value",
      toField: "value"
    }];
    dataSet2.color = "#fac314";
    dataSet2.dataProvider = chartData;
    dataSet2.compared = true;
    dataSet2.title = "East Stock";
    dataSet2.categoryField = "date";

    chart.dataSets = [dataSet, dataSet2];

    // PANELS ///////////////////////////////////////////
    var stockPanel = new AmCharts.StockPanel();
    stockPanel.title = "Value";
    stockPanel.showCategoryAxis = false;
    stockPanel.percentHeight = 70;

    var valueAxis = new AmCharts.ValueAxis();
    valueAxis.dashLength = 5;
    stockPanel.addValueAxis(valueAxis);

    stockPanel.categoryAxis.dashLength = 5;

    // graph of first stock panel
    var graph = new AmCharts.StockGraph();
    graph.type = "candlestick";
    graph.openField = "open";
    graph.closeField = "close";
    graph.highField = "high";
    graph.lowField = "low";
    graph.valueField = "close";
    graph.lineColor = "#7f8da9";
    graph.fillColors = "#7f8da9";
    graph.negativeLineColor = "#db4c3c";
    graph.negativeFillColors = "#db4c3c";
    graph.proCandlesticks = true;
    graph.fillAlphas = 1;
    graph.useDataSetColors = false;
    graph.comparable = true;
    graph.compareField = "value";
    graph.showBalloon = false;
    stockPanel.addStockGraph(graph);

    var stockLegend = new AmCharts.StockLegend();
    stockLegend.valueTextRegular = undefined;
    stockLegend.periodValueTextComparing = "[[percents.value.close]]%";
    stockPanel.stockLegend = stockLegend;

    var chartCursor = new AmCharts.ChartCursor();
    chartCursor.valueLineEnabled = true;
    chartCursor.valueLineAxis = valueAxis;
    stockPanel.chartCursor = chartCursor;

    var stockPanel2 = new AmCharts.StockPanel();
    stockPanel2.title = "Volume";
    stockPanel2.percentHeight = 30;
    stockPanel2.marginTop = 1;
    stockPanel2.showCategoryAxis = true;

    var valueAxis2 = new AmCharts.ValueAxis();
    valueAxis2.dashLength = 5;
    stockPanel2.addValueAxis(valueAxis2);

    stockPanel2.categoryAxis.dashLength = 5;

    var graph2 = new AmCharts.StockGraph();
    graph2.valueField = "volume";
    graph2.type = "column";
    graph2.showBalloon = false;
    graph2.fillAlphas = 1;
    stockPanel2.addStockGraph(graph2);

    var legend2 = new AmCharts.StockLegend();
    legend2.markerType = "none";
    legend2.markerSize = 0;
    legend2.labelText = "";
    legend2.periodValueTextRegular = "[[value.close]]";
    stockPanel2.stockLegend = legend2;

    var chartCursor2 = new AmCharts.ChartCursor();
    chartCursor2.valueLineEnabled = true;
    chartCursor2.valueLineAxis = valueAxis2;
    stockPanel2.chartCursor = chartCursor2;

    chart.panels = [stockPanel, stockPanel2];


    // OTHER SETTINGS ////////////////////////////////////
    var sbsettings = new AmCharts.ChartScrollbarSettings();
    sbsettings.graph = graph;
    sbsettings.graphType = "line";
    sbsettings.usePeriod = "WW";
    sbsettings.updateOnReleaseOnly = false;
    chart.chartScrollbarSettings = sbsettings;


    // PERIOD SELECTOR ///////////////////////////////////
    var periodSelector = new AmCharts.PeriodSelector();
    periodSelector.position = "bottom";
    periodSelector.periods = [{
      period: "DD",
      count: 10,
      label: "10 days"
    }, {
      period: "MM",
      selected: true,
      count: 1,
      label: "1 month"
    }, {
      period: "YYYY",
      count: 1,
      label: "1 year"
    }, {
      period: "YTD",
      label: "YTD"
    }, {
      period: "MAX",
      label: "MAX"
    }];
    chart.periodSelector = periodSelector;

    chart.write('chartdiv');
  }


</script>
      <script type="text/javascript">
       $(function() {
      		/* For zebra striping */
              $("table tr:nth-child(odd)").addClass("odd-row");
      		/* For cell text alignment */
      		$("table td:first-child, table th:first-child").addClass("first");
      		/* For removing the last border */
      		$("table td:last-child, table th:last-child").addClass("last");
      });</script>
</head>
<body>
   <div class="page-container">
   <!--/content-inner-->
	<div class="left-content">
	   <div class="inner-content">
		<!-- header-starts -->
			<div class="header-section">
			<!-- top_bg -->
						<div class="top_bg">

								<div class="header_top">
									<div class="top_right">
										<ul>
											<li>Anyquant, Your quantitative trade expert!</li>
										</ul>
									</div>
									<div class="top_left">
										<h2><span></span> Call us : +86 132 1888 0566</h2>
									</div>
										<div class="clearfix"> </div>
								</div>

						</div>
					<div class="clearfix"></div>
				<!-- /top_bg -->
				</div>
				<div class="header_bg">

							<div class="header">
								<div class="head-t">
									<div class="logo">
										<a href="index.html"><img src="images/logo.png" class="img-responsive" alt=""> </a>
									</div>
										<!-- start header_right -->
									<div class="header_right">
										<div class="rgt-bottom">
											<div class="log">
												<div class="login">
													<div id="loginContainer"><a id="loginButton" class=""><span>Login</span></a>
														<div id="loginBox" style="display: none;">
															<form id="loginForm">
																	<fieldset id="body">
																		<fieldset>
																			  <label for="email">Email Address</label>
																			  <input type="text" name="email" id="email">
																		</fieldset>
																		<fieldset>
																				<label for="password">Password</label>
																				<input type="password" name="password" id="password">
																		 </fieldset>
																		<input type="submit" id="login" value="Sign in">
																		<label for="checkbox"><input type="checkbox" id="checkbox"> <i>Remember me</i></label>
																	</fieldset>
																<span><a href="#">Forgot your password?</a></span>
															</form>
														</div>
													</div>
												</div>
											</div>
											<div class="reg">
												<a href="register.html">REGISTER</a>
											</div>
										<div class="cart box_1">
											<a href="checkout.html">
												<h3> <span class="simpleCart_total">$0.00</span> (<span id="simpleCart_quantity" class="simpleCart_quantity">0</span> items)<img src="images/bag.png" alt=""></h3>
											</a>
											<p><a href="javascript:;" class="simpleCart_empty">(empty card)</a></p>
											<div class="clearfix"> </div>
										</div>
										<div class="create_btn">
											<a href="checkout.html">CHECKOUT</a>
										</div>
										<div class="clearfix"> </div>
									</div>
									<div class="search">
										<form>
											<input type="text" value="" placeholder="search...">
											<input type="submit" value="">
										</form>
									</div>
									<div class="clearfix"> </div>
								</div>
								<div class="clearfix"> </div>
							</div>
						</div>

				</div>
					<!-- //header-ends -->

				<!--content-->
			<div class="content">
					<div class="monthly-grid">
						<div class="panel panel-widget">
							<div class="panel-title">
							<%=session.getAttribute("name") %>&nbsp;<%=session.getAttribute("code") %>

							</div>
							<div class="panel-body">
								<!-- status -->
								<div id="chartdiv" style="width:100%; height:600px;"></div>
								<!-- status -->
							</div>
						</div>
					</div>
				<div class="area">
					<div class="col-md-6 chrt-two area">
						<h3 class="sub-tittle">人气指标</h3>
						<div id="chartdiv1"></div>	
						<%JSONArray js1 = (JSONArray)session.getAttribute("AR");%>
									<script>
									
									var chart1;

						            var chartData1 = <%=js1%>;

						            AmCharts.ready(function () {
						                // generate some random data first
						                

						                // SERIAL CHART
						                chart1 = new AmCharts.AmSerialChart();

						                chart1.marginLeft = 0;
						                chart1.marginRight = 0;
						                chart1.marginTop = 0;
						                chart1.dataProvider = chartData1;
						                chart1.categoryField = "date";

						                // AXES
						                // category
						                var categoryAxis = chart1.categoryAxis;
						                categoryAxis.parseDates = true; // as our data is date-based, we set parseDates to true
						                categoryAxis.minPeriod = "DD"; // our data is daily, so we set minPeriod to DD
						                // value axis
						                var valueAxis = new AmCharts.ValueAxis();
						                valueAxis.inside = true;
						                valueAxis.tickLength = 0;
						                valueAxis.axisAlpha = 0;
						                valueAxis.minimum = 0;
						                valueAxis.maximum = 200;
						                chart1.addValueAxis(valueAxis);

						                // GRAPH
						                var graph = new AmCharts.AmGraph();
						                graph.dashLength = 3;
						                graph.lineColor = "#7717D7";
						                graph.valueField = "AR";
						                graph.dashLength = 3;
						                graph.bullet = "round";
						                chart1.addGraph(graph);

						                // CURSOR
						                var chartCursor = new AmCharts.ChartCursor();
						                chartCursor.cursorAlpha = 0;
						                chart1.addChartCursor(chartCursor);

						                // GUIDES are used to create horizontal range fills
						                var guide = new AmCharts.Guide();
						                guide.value = 120;
						                guide.toValue = 200;
						                guide.fillColor = "#CC0000";
						                guide.fillAlpha = 0.2;
						                guide.lineAlpha = 0;
						                valueAxis.addGuide(guide);


						                var guide = new AmCharts.Guide();
						                guide.value = 80;
						                guide.toValue = 120;
						                guide.fillColor = "#CC0000";
						                guide.fillAlpha = 0.05;
						                guide.lineAlpha = 0;
						                valueAxis.addGuide(guide);

						                var guide = new AmCharts.Guide();
						                guide.value = 0;
						                guide.toValue = 80;
						                guide.fillColor = "#0000cc";
						                guide.fillAlpha = 0.05;
						                guide.lineAlpha = 0;
						                valueAxis.addGuide(guide);



						                // WRITE
						                chart1.write("chartdiv1");
						            });

									</script>
					</div>
					<div class="col-md-6 chrt-three area">
						<h3 class="sub-tittle">意愿指标</h3>
							<div id="chartdiv2"></div>	
							<%JSONArray js2 = (JSONArray)session.getAttribute("BR");%>
							<script>
									
									var chart2;

						            var chartData2 = <%=js2%>;

						            AmCharts.ready(function () {
						                // generate some random data first
						                

						                // SERIAL CHART
						                chart2 = new AmCharts.AmSerialChart();

						                chart2.marginLeft = 0;
						                chart2.marginRight = 0;
						                chart2.marginTop = 0;
						                chart2.dataProvider = chartData2;
						                chart2.categoryField = "date";

						                // AXES
						                // category
						                var categoryAxis = chart2.categoryAxis;
						                categoryAxis.parseDates = true; // as our data is date-based, we set parseDates to true
						                categoryAxis.minPeriod = "DD"; // our data is daily, so we set minPeriod to DD
						                // value axis
						                var valueAxis = new AmCharts.ValueAxis();
						                valueAxis.inside = true;
						                valueAxis.tickLength = 0;
						                valueAxis.axisAlpha = 0;
						                valueAxis.minimum = 0;
						                valueAxis.maximum = 200;
						                chart2.addValueAxis(valueAxis);

						                // GRAPH
						                var graph = new AmCharts.AmGraph();
						                graph.dashLength = 3;
						                graph.lineColor = "#7717D7";
						                graph.valueField = "BR";
						                graph.dashLength = 3;
						                graph.bullet = "round";
						                chart2.addGraph(graph);

						                // CURSOR
						                var chartCursor = new AmCharts.ChartCursor();
						                chartCursor.cursorAlpha = 0;
						                chart2.addChartCursor(chartCursor);

						                // GUIDES are used to create horizontal range fills
						                var guide = new AmCharts.Guide();
						                guide.value = 120;
						                guide.toValue = 200;
						                guide.fillColor = "#CC0000";
						                guide.fillAlpha = 0.2;
						                guide.lineAlpha = 0;
						                valueAxis.addGuide(guide);


						                var guide = new AmCharts.Guide();
						                guide.value = 80;
						                guide.toValue = 120;
						                guide.fillColor = "#CC0000";
						                guide.fillAlpha = 0.05;
						                guide.lineAlpha = 0;
						                valueAxis.addGuide(guide);

						                var guide = new AmCharts.Guide();
						                guide.value = 0;
						                guide.toValue = 80;
						                guide.fillColor = "#0000cc";
						                guide.fillAlpha = 0.05;
						                guide.lineAlpha = 0;
						                valueAxis.addGuide(guide);
						                // WRITE
						                chart2.write("chartdiv2");
						            });

									</script>
					</div>
					<div class="clearfix"></div>
				</div>
						<!--//area-->
							<div class="col-md-7 mid-content-top">
								<div class="middle-content" style="width:100%; height:500px; overflow:scroll; overflow-x:hidden;">
									<h3>股票列表
									</h3>
									<!-- start content_slider -->
                  <table>
                    <thead>
                      <tr>
                        <th>日期</th>
                        <th>开盘价</th>
                        <th>收盘价</th>
                        <th>最高价</th>
                        <th>最低价</th>
                        <th>后复权价</th>
                        <th>成交量</th>
                        <th>转手率</th>
                        <th>市盈率</th>
                        <th>市净率</th>
                      </tr>
                    </thead>
                    <tbody>



                    <%
                       List<ConcreteInfoVO> al=(List<ConcreteInfoVO>)session.getAttribute("concrete");
                        for (ConcreteInfoVO vo : al) {     %>
                          <tr>
                            <td><%=vo.getDate()%></td>
                            <td><%=vo.getOpen()%></td>
                            <td><%=vo.getClose()%></td>
                            <td><%=vo.getHighest()%></td>
                            <td><%=vo.getLowest()%></td>
                            <td><%=vo.getAdj_price()%></td>
                            <td><%=vo.getVolume()%></td>
                            <td><%=vo.getTurnover()%></td>
						 	<td><%=vo.getPe()%></td>
                            <td><%=vo.getPb()%></td>
                          </tr>
                  <%   }   %>

                    </tbody>

                  </table>

										</div>
								</div>
								<!--//sreen-gallery-cursual---->
								<!-- requried-jsfiles-for owl -->
								<link href="css/owl.carousel.css" rel="stylesheet">
								<script src="js/owl.carousel.js"></script>
									<script>
										$(document).ready(function() {
											$("#owl-demo").owlCarousel({
												items : 3,
												lazyLoad : true,
												autoPlay : true,
												pagination : true,
												nav:true,
											});
										});
									</script>
								<!-- //requried-jsfiles-for owl -->
						</div>
						<div class="clearfix"></div>

					<div class="content-top">


		<div class="clearfix"> </div>
		</div>
		<!--area-->

						<!--//area-->
		<div class="fo-top-di">
			<div class="foot-top">

					<div class="col-md-6 s-c">
						<li>
							<div class="fooll">
								<h1>follow us on</h1>
							</div>
						</li>
						<li>
							<div class="social-ic">
								<ul>
									<li><a href="#"><i class="facebok"> </i></a></li>
									<li><a href="#"><i class="twiter"> </i></a></li>
									<li><a href="#"><i class="goog"> </i></a></li>
									<li><a href="#"><i class="be"> </i></a></li>
										<div class="clearfix"></div>
								</ul>
							</div>
						</li>
							<div class="clearfix"> </div>
					</div>
					<div class="col-md-6 s-c">
						<div class="stay">
									<div class="stay-left">
										<form>
											<input type="text" placeholder="Enter your email" required="">
										</form>
									</div>
									<div class="btn-1">
										<form>
											<input type="submit" value="join">
										</form>
									</div>
										<div class="clearfix"> </div>
						</div>
					</div>
					<div class="clearfix"> </div>

			</div>
			<div class="footer">
					<div class="col-md-3 cust">
						<h4>CUSTOMER CARE</h4>
							<li><a href="contact.html">Help Center</a></li>
							<li><a href="faq.html">FAQ</a></li>
							<li><a href="details.html">How To Buy</a></li>
							<li><a href="checkout.html">Delivery</a></li>
					</div>
					<div class="col-md-2 abt">
						<h4>ABOUT US</h4>
							<li><a href="products.html">Our Stories</a></li>
							<li><a href="products.html">Press</a></li>
							<li><a href="faq.html">Career</a></li>
							<li><a href="contact.html">Contact</a></li>
					</div>
					<div class="col-md-2 myac">
						<h4>MY ACCOUNT</h4>
							<li><a href="register.html">Register</a></li>
							<li><a href="checkout.html">My Cart</a></li>
							<li><a href="checkout.html">Order History</a></li>
							<li><a href="details.html">Payment</a></li>
					</div>
					<div class="col-md-5 our-st">
						<div class="our-left">
							<h4>OUR STORES</h4>
						</div>

							<li><i class="add"> </i>Mark peter</li>
							<li><i class="phone"> </i>012-586987</li>
							<li><a href="mailto:info@example.com"><i class="mail"> </i>info@sitename.com </a></li>
					</div>
					<div class="clearfix"> </div>
						<p>Copyright &copy; 2016.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
			</div>
		</div>
			</div>
			<!--content-->
		</div>

				<!--//content-inner-->
			<!--/sidebar-menu-->
				<div class="sidebar-menu">
					<header class="logo1">
						<a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span> </a>
					</header>
						<div style="border-top:1px ridge rgba(255, 255, 255, 0.15)"></div>
                           <div class="menu">
									<ul id="menu" >
										<li><a href="home.jsp"><i class="fa fa-tachometer"></i> <span>主页</span></a></li>
										 <li id="menu-academico" ><a href="#"><i class="fa fa-table"></i> <span>我的收藏</span> </a></li>
										 <li id="menu-academico" ><a href="sunglasses.html"><i class="fa fa-file-text-o"></i> <span>股票列表</span></a></li>
									<li><a href="sweater.html"><i class="fa fa-file-text-o"></i> <span>行业分析</span></a></li>
									<li id="menu-academico" ><a href="catalog.html"><i class="fa fa-file-text-o"></i> <span>股票对比</span></a></li>
									<li id="menu-academico" ><a href="shoes.html"><i class="fa fa-file-text-o"></i> <span>形势预测</span></a></li>
								  </ul>
								</div>
							  </div>
      </div>
							  <div class="clearfix"></div>
							</div>
							<script>
							var toggle = true;

							$(".sidebar-icon").click(function() {
							  if (toggle)
							  {
								$(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
								$("#menu span").css({"position":"absolute"});
							  }
							  else
							  {
								$(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
								setTimeout(function() {
								  $("#menu span").css({"position":"relative"});
								}, 400);
							  }

											toggle = !toggle;
										});
							</script>
<!--js -->
<script src="js/jquery.nicescroll.js"></script>
<script src="js/scripts.js"></script>
<!-- Bootstrap Core JavaScript -->
   <script src="js/bootstrap.min.js"></script>
   <!-- /Bootstrap Core JavaScript -->
   <!-- real-time -->
<script language="javascript" type="text/javascript" src="js/jquery.flot.js"></script>
	

		   <script src="js/menu_jquery.js"></script>
</body>
</html>
