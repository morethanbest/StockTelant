<%@page import="net.sf.json.JSONArray"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@page import="org.springframework.context.ApplicationContext" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@page import="vo.OriginInfoVO" %>
<%@page import="controller.*" %>
<%@page import="Date.*" %>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="vo.DealPieVO"%>

<!DOCTYPE HTML>
<html>
<head>
<title>stockprofession</title>
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
<script src="amcharts/pie.js" type="text/javascript"></script>
<script src="amcharts/serial.js" type="text/javascript"></script>
<%
ApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
GraphController gc =(GraphController)context.getBean("GraphController");  
DateServ dv =new DateServ(); 
List<DealPieVO> list=gc.getIndustryPie(dv.getDate(),dv.getDateBefore(30));
%>
        <script>
           var chart;
           var chartData = [];
            var chart1;
            var chartData1 = [
                {
                    "industry": '<%=list.get(0).getName()%>',
                    "value": 1000
                },
                {
                    "industry": '<%=list.get(1).getName()%>',
                    "value": 1882
                },
                {
                    "industry": '<%=list.get(2).getName()%>',
                    "value": 1809
                },
                {
                    "industry": '<%=list.get(3).getName()%>',
                    "value": 1322
                },
                {
                    "industry": '<%=list.get(4).getName()%>',
                    "value": 1122
                },
                {
                    "industry": '<%=list.get(5).getName()%>',
                    "value": 1114
                }
            ];
           AmCharts.ready(function () {
               // generate some random data first
               generateChartData();

               // SERIAL CHART
               chart = new AmCharts.AmSerialChart();

               chart.dataProvider = chartData;
               chart.categoryField = "date";

               // listen for "dataUpdated" event (fired when chart is inited) and call zoomChart method when it happens
               chart.addListener("dataUpdated", zoomChart);

               chart.synchronizeGrid = true; // this makes all axes grid to be at the same intervals

               // AXES
               // category
               var categoryAxis = chart.categoryAxis;
               categoryAxis.parseDates = true; // as our data is date-based, we set parseDates to true
               categoryAxis.minPeriod = "DD"; // our data is daily, so we set minPeriod to DD
               categoryAxis.minorGridEnabled = true;
               categoryAxis.axisColor = "#DADADA";
               categoryAxis.twoLineMode = true;
               categoryAxis.dateFormats = [{
                    period: 'fff',
                    format: 'JJ:NN:SS'
                }, {
                    period: 'ss',
                    format: 'JJ:NN:SS'
                }, {
                    period: 'mm',
                    format: 'JJ:NN'
                }, {
                    period: 'hh',
                    format: 'JJ:NN'
                }, {
                    period: 'DD',
                    format: 'DD'
                }, {
                    period: 'WW',
                    format: 'DD'
                }, {
                    period: 'MM',
                    format: 'MMM'
                }, {
                    period: 'YYYY',
                    format: 'YYYY'
                }];

               // first value axis (on the left)
               var valueAxis1 = new AmCharts.ValueAxis();
               valueAxis1.axisColor = "#FF6600";
               valueAxis1.axisThickness = 2;
               chart.addValueAxis(valueAxis1);

               // second value axis (on the right)
               var valueAxis2 = new AmCharts.ValueAxis();
               valueAxis2.position = "right"; // this line makes the axis to appear on the right
               valueAxis2.axisColor = "#FCD202";
               valueAxis2.gridAlpha = 0;
               valueAxis2.axisThickness = 2;
               chart.addValueAxis(valueAxis2);

               // third value axis (on the left, detached)
               var valueAxis3 = new AmCharts.ValueAxis();
               valueAxis3.offset = 50; // this line makes the axis to appear detached from plot area
               valueAxis3.gridAlpha = 0;
               valueAxis3.axisColor = "#B0DE09";
               valueAxis3.axisThickness = 2;
               chart.addValueAxis(valueAxis3);

               // GRAPHS
               // first graph
               var graph1 = new AmCharts.AmGraph();
               graph1.valueAxis = valueAxis1; // we have to indicate which value axis should be used
               graph1.title = "red line";
               graph1.valueField = "visits";
               graph1.bullet = "round";
               graph1.hideBulletsCount = 30;
               graph1.bulletBorderThickness = 1;
               chart.addGraph(graph1);

               // second graph
               var graph2 = new AmCharts.AmGraph();
               graph2.valueAxis = valueAxis2; // we have to indicate which value axis should be used
               graph2.title = "yellow line";
               graph2.valueField = "hits";
               graph2.bullet = "square";
               graph2.hideBulletsCount = 30;
               graph2.bulletBorderThickness = 1;
               chart.addGraph(graph2);

               // third graph
               var graph3 = new AmCharts.AmGraph();
               graph3.valueAxis = valueAxis3; // we have to indicate which value axis should be used
               graph3.valueField = "views";
               graph3.title = "green line";
               graph3.bullet = "triangleUp";
               graph3.hideBulletsCount = 30;
               graph3.bulletBorderThickness = 1;
               chart.addGraph(graph3);

               // CURSOR
               var chartCursor = new AmCharts.ChartCursor();
               chartCursor.cursorAlpha = 0.1;
               chartCursor.fullWidth = true;
               chartCursor.valueLineBalloonEnabled = true;
               chart.addChartCursor(chartCursor);

               // SCROLLBAR
               var chartScrollbar = new AmCharts.ChartScrollbar();
               chart.addChartScrollbar(chartScrollbar);

               // LEGEND
               var legend = new AmCharts.AmLegend();
               legend.marginLeft = 110;
               legend.useGraphSettings = true;
               chart.addLegend(legend);

               // WRITE
               chart.write("chartdiv");
                               // PIE CHART
                chart1 = new AmCharts.AmPieChart();

                // title of the chart
                chart1.addTitle("Visitors countries", 16);

                chart1.dataProvider = chartData1;
                chart1.titleField = "industry";
                chart1.valueField = "value";
                chart1.sequencedAnimation = true;
                chart1.startEffect = "elastic";
                chart1.innerRadius = "30%";
                chart1.startDuration = 2;
                chart1.labelRadius = 15;
                chart1.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
                // the following two lines makes the chart 3D
                chart1.depth3D = 10;
                chart1.angle = 15;

                // WRITE
                chart1.write("chartdiv1");
           });

           // generate some random data, quite different range
           function generateChartData() {
               var firstDate = new Date();
               firstDate.setDate(firstDate.getDate() - 50);

               for (var i = 0; i < 50; i++) {
                   // we create date objects here. In your data, you can have date strings
                   // and then set format of your dates using chart.dataDateFormat property,
                   // however when possible, use date objects, as this will speed up chart rendering.
                   var newDate = new Date(firstDate);
                   newDate.setDate(newDate.getDate() + i);

                   var visits = Math.round(Math.random() * 40) + 100;
                   var hits = Math.round(Math.random() * 80) + 500;
                   var views = Math.round(Math.random() * 6000);

                   chartData.push({
                       date: newDate,
                       visits: visits,
                       hits: hits,
                       views: views
                   });
               }
           }

           // this method is called when chart is first inited as we listen for "dataUpdated" event
           function zoomChart() {
               // different zoom methods can be used - zoomToIndexes, zoomToDates, zoomToCategoryValues
               chart.zoomToIndexes(10, 20);
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
							  行业热门情况

							</div>
							<div class="panel-body">
								<!-- status -->
								<div id="chartdiv1" style="width:100%; height:600px;"></div>
								<!-- status -->
							</div>
						</div>
					</div>

						<!--//area-->
							<div class="col-md-7 mid-content-top">
								<div class="middle-content" style="width:100%; height:500px; overflow:scroll; overflow-x:hidden;">
									<h3>行业
									</h3>
									<!-- start content_slider -->
                  <table>
                    <thead>
                      <tr>
                        <th>日期</th>
                        <th>股票代号</th>
                        <th>股票名称</th>
                        <th>开盘价</th>
                        <th>收盘价</th>
                        <th>最高价</th>
                        <th>最低价</th>
                        <th>成交量</th>
                      </tr>
                    </thead>
                    <tbody>

                          <tr>
                            <td>0</td>
                            <td>0</td>
                            <td>0</td>
                            <td>0</td>
                            <td>0</td>
                            <td>0</td>
                            <td>0</td>
                            <td>0</td>
                          </tr>
                     

                    </tbody>

                  </table>
                  

										</div>
								</div>
				   <div class="monthly-grid">
						<div class="panel panel-widget">
							<div class="panel-title">
							  行业成交量折线图
							</div>
							<div class="panel-body">
								<!-- status -->
								<div id="chartdiv" style="width:100%; height:400px;"></div>
								<!-- status -->
							</div>
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
