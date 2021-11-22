

<%@page import="common.VacciBean"%>
<%@page import="java.time.Period"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.NotificationBean"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<%
    dbConnection.DbConnection con = new dbConnection.DbConnection();

    String key = request.getParameter("key").trim();
    System.out.println("key=" + key);

    //login
    if (key.equals("login")) {
        String id = "";
        String qry = "select UserId,Type from login where UserName='" + request.getParameter("uname") + "'and Password='" + request.getParameter("pass") + "' ";
        String username = request.getParameter("uname");
        System.out.println("qry=" + qry);
        Iterator it = con.getData(qry).iterator();
        if (it.hasNext()) {
            Vector v = (Vector) it.next();
            id = "" + v.get(0) + ":" + v.get(1) + "";
            System.out.println("id" + id);
            out.print(id);
        } else {
            System.out.println("else id=" + id);
            out.print("failed");
        }
    }

    //add users
    if (key.equals("AshaAddUser")) {

        String aid = request.getParameter("aid");
        String name = request.getParameter("name");
        String housenumber = request.getParameter("house");
        String husname = request.getParameter("husname");
        String month = request.getParameter("month");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String image = request.getParameter("image");
        
        
        System.out.println("Testttttt " + month);
        
        String pass = new String(common.Utilities.OTP(6));

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        String strDate = format.format(date);

        String qry = "INSERT INTO `tbl_user` (`aid`,hnumber,`name`,`dob`,carring_month,address,`phone`,`email`,photo,`date`) VALUES('" + aid + "','" + housenumber + "','" + name + "','" + dob + "','" + month + "','" + address + "','" + phone + "','" + email + "','" + image + "','" + strDate + "') ";
        String qry1 = "insert into login (UserId,username,password,type)values((select max(uid)from tbl_user),'" + email + "','" + pass + "','user')";

        if (con.putData(qry) > 0) {
            if (con.putData(qry1) > 0) {
                out.print("successful");
            }
        } else {
            out.print("failed");
        }
    }

    //add childs 
    if (key.equals("AshaAddChildUser")) {

        String aid = request.getParameter("aid");
        String house = request.getParameter("house");
        String mid = request.getParameter("mid");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        String strDate = format.format(date);

        String qry = "INSERT INTO `tbl_childregister` (aid,hnumber,mid,name,gender,dob,date) VALUES('" + aid + "','" + house + "','" + mid + "','" + name + "','" + gender + "','" + dob + "','" + strDate + "') ";

        System.out.println(qry);

        if (con.putData(qry) > 0) {

            out.print("successful");

        } else {
            out.print("failed");
        }
    }

//ashaworker broadcasting emergency messages
    if (key.equals("AshaBroadMsg")) {
        System.out.print("haii");
        String aid = request.getParameter("aid");
        String sub = request.getParameter("sub");
        String msg = request.getParameter("msg");
        String qry1 = "SELECT phone FROM `tbl_user` WHERE aid=" + aid;
        String phn = "";
        Iterator it1 = con.getData(qry1).iterator();
        while (it1.hasNext()) {
            Vector v = (Vector) it1.next();
            phn += v.get(0) + "#";
            System.out.println();
        }
        String qry = "INSERT INTO `tbl_emergmsg` (`aid`,`subject`,`message`) VALUES('" + aid + "','" + sub + "','" + msg + "') ";

        System.out.print(qry);

        if (con.putData(qry) > 0) {

            out.print(phn);
        } else {
            out.print("failed");
        }
    }

//to get registered mother names in one house
    if (key.equals("AshaGetMother")) {
        System.out.print("haii");
        String aid = request.getParameter("aid");
        String hnumber = request.getParameter("houseNo");
        String qry1 = "SELECT uid,NAME FROM `tbl_user` WHERE hnumber='" + hnumber + "' AND aid=" + aid;
        System.out.println(qry1);
        String data = "", id = "", name = "";
        Iterator it1 = con.getData(qry1).iterator();
        if (it1.hasNext()) {
            while (it1.hasNext()) {
                Vector v = (Vector) it1.next();
                id += v.get(0) + ":";
                name += v.get(1) + ":";
            }
            data = id + "#" + name;
            out.print(data);
        } else {
            out.print("NotFound");
        }

    }

//*****Notifications******
//Food****Notifcation
    if (key.equals("AshaFoodNotification")) {

        String aid = request.getParameter("aid");
        String usertype = request.getParameter("usertype");
        String foodname = request.getParameter("foodname");
        String descript = request.getParameter("descript");
        String fdate = request.getParameter("fdate");

        String qry = "INSERT INTO `tbl_food_notification` (aid,usertype,foodname,descript,date) VALUES('" + aid + "','" + usertype + "','" + foodname + "','" + descript + "','" + fdate + "') ";
        System.out.println(qry);

        if (con.putData(qry) > 0) {

            out.print("successful");

        } else {
            out.print("failed");
        }
    }
//    
//    //Program Notifications
    if (key.equals("AshaProgramNotification")) {

        String aid = request.getParameter("aid");
        String progname = request.getParameter("progname");
        String pdate = request.getParameter("pdate");
        String plocat = request.getParameter("plocat");
        String ptime = request.getParameter("ptime");
        String pdescript = request.getParameter("pdescript");

        String qry = "INSERT INTO `tbl_program_notification` (aid,progname,pdate,plocation,ptime,description) VALUES('" + aid + "','" + progname + "','" + pdate + "','" + plocat + "','" + ptime + "','" + pdescript + "') ";

        System.out.println(qry);

        if (con.putData(qry) > 0) {

            out.print("successful");

        } else {
            out.print("failed");
        }
    }

    if (key.equals("AshaViewPanPrograms")) {

        String aid = request.getParameter("aid");
        List<VacciBean> allVacciInfos = new ArrayList<VacciBean>();

        String data = "";
        String info = "";

        String qry = "SELECT p.ProgName,p.PanProLoc,p.PanProDate,p.progtime,p.ProgDet FROM panprograms p,regasha a WHERE p.PID=a.PID AND a.AID='" + aid + "' ";
        System.out.println(qry);
        Iterator it1 = con.getData(qry).iterator();
        if (it1.hasNext()) {
            while (it1.hasNext()) {
                Vector v = (Vector) it1.next();
//                data = "Prgm Name  : " + v.get(0) + "\nLocation        :  " + v.get(1) + "\nDate               :  " + v.get(2) + "\nTime              :  " + v.get(3) + "\nDetails           :  " + v.get(4) + "\n";
//                System.out.println(data);
//                info += data + "#";
//            }
//            out.print(info);
//        } else {
//            out.print("failed");
//
//        }
//    }
                VacciBean vbean = new VacciBean();
                vbean.setProgName(v.get(0).toString());
                vbean.setPanProLoc(v.get(1).toString());
                vbean.setPanProDate(v.get(2).toString());
                vbean.setProgtime(v.get(3).toString());
                vbean.setProgDet(v.get(4).toString());
                allVacciInfos.add(vbean);
            }
            Gson gson = new Gson();

            info = gson.toJson(allVacciInfos);

            System.out.println(info);
            if (info.isEmpty()) {
                info = "failed";
            }
            out.println(info);
        } else {
            System.out.println(info);
            out.println("failed");
        }
    }
    if (key.equals("UserViewPanPrograms")) {

        String uid = request.getParameter("uid");
        List<VacciBean> allVacciInfos = new ArrayList<VacciBean>();

        String data = "";
        String info = "";

        String qry = "SELECT p.ProgName,p.PanProLoc,p.PanProDate,p.progtime,p.ProgDet FROM panprograms p,regasha a,tbl_user u WHERE u.`aid`=a.`AID` AND a.`PID`=p.`PID` AND u.`uid`=" + uid;
        System.out.println(qry);
        Iterator it1 = con.getData(qry).iterator();
        if (it1.hasNext()) {
            while (it1.hasNext()) {
                Vector v = (Vector) it1.next();
//                data = "Prgm Name      : " + v.get(0) + "\nLocation           :  " + v.get(1) + "\nDate                  :  " + v.get(2) + "\nTime                  :  " + v.get(3) + "\nDetails              :  " + v.get(4) + "\n" + "#";
//                System.out.println(data);
//                info += data;
//            }
//            out.print(info);
//        } else {
//            out.print("failed");
//
//        }
                VacciBean vbean = new VacciBean();
                vbean.setProgName(v.get(0).toString());
                vbean.setPanProLoc(v.get(1).toString());
                vbean.setPanProDate(v.get(2).toString());
                vbean.setProgtime(v.get(3).toString());
                vbean.setProgDet(v.get(4).toString());
                allVacciInfos.add(vbean);
            }
            Gson gson = new Gson();

            info = gson.toJson(allVacciInfos);

            System.out.println(info);
            if (info.isEmpty()) {
                info = "failed";
            }
            out.println(info);
        } else {
            System.out.println(info);
            out.println("failed");
        }
    }

    if (key.equals("AshaViewgovPrograms")) {

        List<VacciBean> allVacciInfos = new ArrayList<VacciBean>();

        String data = "";
        String info = "";

        String qry = "SELECT pro_name,pro_user,pro_det,pro_date FROM admprojects ";
        System.out.println(qry);
        Iterator it1 = con.getData(qry).iterator();
        if (it1.hasNext()) {
            while (it1.hasNext()) {
                Vector v = (Vector) it1.next();
//                data = "Project Name :  " + v.get(0) + "\nProject User  " + v.get(1) + "\nProject Details " + v.get(2) + "\nProject Date " + v.get(3) + "\n";
                // System.out.println(data);
//                info += data + "#";
//bean class
                VacciBean vbean = new VacciBean();
                vbean.setPro_name(v.get(0).toString());
                vbean.setPro_user(v.get(1).toString());
                vbean.setPro_date(v.get(3).toString());
                vbean.setPro_det(v.get(2).toString());

                allVacciInfos.add(vbean);
            }
            Gson gson = new Gson();

            info = gson.toJson(allVacciInfos);

            System.out.println(info);
            if (info.isEmpty()) {
                info = "failed";
            }
            out.println(info);
        } else {
            System.out.println(info);
            out.println("failed");
        }
    }

    if (key.equals("UserViewVacciDetails")) {

        String uid = request.getParameter("uid");
        String usertype = request.getParameter("usertype");
        List<VacciBean> allVacciInfos = new ArrayList<VacciBean>();

        String data = "";
        String info = "";
        String qry = "SELECT v.vid,v.vacci_name,v.vacci_age,v.details FROM tbl_vaccination v,regasha a,tbl_user u WHERE u.aid=a.AID AND a.PID=v.PID AND u.uid=" + uid + " AND v.usertype='" + usertype + "'";
        System.out.println(qry);
        Iterator it1 = con.getData(qry).iterator();
        String vname = " ", age = " ", det = " ";
        String testdata = "";
        if (it1.hasNext()) {

            while (it1.hasNext()) {
                Vector v = (Vector) it1.next();
//                vname += "Vacci Name   :" + v.get(0);
//                age += "Age          :" + v.get(1);
//                det += "Details      :" + v.get(2);
//                System.out.println(vname);
//                System.out.println(age);
//                System.out.println(det);
//                testdata = testdata + "Vaccin       " + v.get(0) + "\nAge               " + v.get(1) + "\nDetails       " + v.get(2) + "#";
                VacciBean vbean = new VacciBean();
                vbean.setVacci_id(v.get(0).toString());
                vbean.setVacci_name(v.get(1).toString());
                vbean.setVacci_details(v.get(3).toString());
                allVacciInfos.add(vbean);
            }
//            data += vname + "\n" + age + "\n" + det + "\n" + "#";
//            out.print(testdata);
//            System.out.println(testdata);
//        } else {
//            out.print("failed");
//
//        }
            Gson gson = new Gson();

            info = gson.toJson(allVacciInfos);

            System.out.println(info);
            if (info.isEmpty()) {
                info = "failed";
            }
            out.println(info);
        } else {
            System.out.println(info);
            out.println("failed");
        }
//        Sy
    }
    if (key.equals("UserViewFoodDetails")) {
        String uid = request.getParameter("uid");
        String usertype = request.getParameter("usertype");
        List<VacciBean> allVacciInfos = new ArrayList<VacciBean>();

        String data = "";
        String info = "";
        String fname = "", period = "", det = "";
        String qry = "SELECT f.FoodName,f.FoodPeriod,f.FoodDetails FROM tbl_food f,regasha a,tbl_user u WHERE u.aid=a.AID AND a.PID=f.PID AND u.uid='" + uid + "' AND f.FoodUserType='" + usertype + "'";
        System.out.println(qry);
        Iterator it1 = con.getData(qry).iterator();

        if (it1.hasNext()) {
            while (it1.hasNext()) {
                Vector v = (Vector) it1.next();
//                fname += "Food Name :" + v.get(0);
//                period += "Period         :" + v.get(1);
//                det += "Details        :" + v.get(2);
//                System.out.println(fname);
//                System.out.println(period);
//                System.out.println(det);
                VacciBean vbean = new VacciBean();
                vbean.setFoodName(v.get(0).toString());
                vbean.setFoodPeriod(v.get(1).toString());
                vbean.setFoodDetails(v.get(2).toString());
                allVacciInfos.add(vbean);
            }
//            data += vname + "\n" + age + "\n" + det + "\n" + "#";
//            out.print(testdata);
//            System.out.println(testdata);
//        } else {
//            out.print("failed");
//
//        }
            Gson gson = new Gson();

            info = gson.toJson(allVacciInfos);

            System.out.println(info);
            if (info.isEmpty()) {
                info = "failed";
            }
            out.println(info);
        } else {
            System.out.println(info);
            out.println("failed");
        }
//            }
//            data += fname + "\n" + period + "\n" + det + " \n" + "#";
//            out.print(data);
//        } else {
//            out.print("failed");
//        }
    }
    //Asha requests users details
    if (key.equals("AshaViewUsersDetails")) {
        String aid = request.getParameter("aid");
        String usertype = request.getParameter("usertype");
        String data = "";
        String info = "";
        String id = "", users = "", hnumber = "";
        String qry = "SELECT uid,name, hnumber FROM tbl_user WHERE aid='" + aid + "'";
        System.out.println(qry);
        Iterator it1 = con.getData(qry).iterator();
        if (it1.hasNext()) {
            data = "Name        HouseNo";
            while (it1.hasNext()) {
                Vector v = (Vector) it1.next();
                id += v.get(0) + ":";
                users += v.get(1) + "               " + v.get(2) + ":";
                //hnumber+=v.get(2) + "";
                System.out.println(id);
                System.out.println(users);
                System.out.println(hnumber);
            }
            data = id + "#" + users + "\n\n";
            System.out.print(data.trim());
            out.print(data.trim());
        } else {
            out.print("failed");
        }
    }
//getUserDetails start

//Asha requests users details
    if (key.equals("getUserDetails")) {
        String uid = request.getParameter("uid");
        String users = "";
        String qry = "SELECT * FROM `tbl_user` WHERE uid=" + uid;
        //System.out.println(qry);
        Iterator it1 = con.getData(qry).iterator();
        if (it1.hasNext()) {
            while (it1.hasNext()) {
                Vector v = (Vector) it1.next();
                users += v.get(0) + ":" + v.get(2) + ":" + v.get(3) + ":" + v.get(4) + ":" + v.get(5) + ":" + v.get(6) + ":" + v.get(7) + ":" + v.get(8) + ":" + v.get(9);
                System.out.println(users);
            }
            System.out.print(users);
            out.print(users);
        } else {
            out.print("failed");
        }
    }
//getUserDetails start

//start ashaDeleteUser
    if (key.equals("ashaDeleteUser")) {
        String uid = request.getParameter("uid");
        System.out.println("idddd is "+uid);
        String qry = "Delete FROM tbl_user WHERE uid=" + uid;
        System.out.println(qry);

        if (con.putData(qry) > 0) {

            System.out.println("Deleted");
        } else {
            out.print("failed");
        }
    }
//end ashaDeleteUser
//getFoodNotification start

    if (key.equals("getFoodNotification")) {
        System.out.println("haii");
        List<NotificationBean> allInfos = new ArrayList<NotificationBean>();

        String station = request.getParameter("uid");
        String aid = "";
        String qry = " SELECT `aid` FROM `tbl_user` WHERE `uid`='" + station + "' ";
        Iterator it3 = con.getData(qry).iterator();
        if (it3.hasNext()) {
            Vector v3 = (Vector) it3.next();
            aid = v3.get(0).toString();
        }

        String str = "SELECT `foodid`,`foodname`,`descript`,`date` FROM `tbl_food_notification` where `aid`='" + aid + "'";
//        String str = "SELECT `foodid`,`foodname`,`descript`,`date` FROM `tbl_food_notification` ";

        System.out.println("qry=" + str);
        String data = "";
        String infoall = "";
        Iterator it = con.getData(str).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector vv = (Vector) it.next();
                LocalDate today = LocalDate.now();
                String datee = vv.get(3).toString();
                LocalDate date = LocalDate.parse(datee);
                Period period = Period.between(today, date);
                if ((period.getDays() >= 0) && (period.getMonths() >= 0) && (period.getYears() >= 0)) {
                    NotificationBean bean = new NotificationBean();
                    bean.setFoodnot_id(vv.get(0).toString());
                    bean.setFood_name(vv.get(1).toString());
                    bean.setFood_date(vv.get(3).toString());
                    bean.setFood_decription(vv.get(2).toString());
                    allInfos.add(bean);
                }
            }
            Gson gson = new Gson();

            infoall = gson.toJson(allInfos);

            System.out.println(infoall);
            if (infoall.isEmpty()) {
                infoall = "failed";
            }
            out.println(infoall);
        } else {
            System.out.println(infoall);
            out.println("failed");
        }
//        System.out.println(infoall);
    }

//getFoodNotification end
//getProgramNotification start
    if (key.equals("getProgramNotification")) {
        System.out.println("haii");
        List<NotificationBean> allInfos = new ArrayList<NotificationBean>();

        String station = request.getParameter("uid");
        String aid = "";
        String qry = " SELECT `aid` FROM `tbl_user` WHERE `uid`='" + station + "' ";
        Iterator it3 = con.getData(qry).iterator();
        if (it3.hasNext()) {
            Vector v3 = (Vector) it3.next();
            aid = v3.get(0).toString();
        }

        String str = "SELECT `progid`,`progname`,`pdate`,`plocation`,`ptime`,`description` FROM `tbl_program_notification` where `aid`='" + aid + "'";
        //String str = "SELECT `progid`,`progname`,`pdate`,`plocation`,`ptime`,`description` FROM `tbl_program_notification`";
        System.out.println("qry=" + str);

        String data = "";
        String infoall = "";
        Iterator it = con.getData(str).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector vv = (Vector) it.next();
                LocalDate today = LocalDate.now();
                String datee = vv.get(2).toString();
                LocalDate date = LocalDate.parse(datee);
                Period period = Period.between(today, date);
                if ((period.getDays() >= 0) && (period.getMonths() >= 0) && (period.getYears() >= 0)) {
                    NotificationBean bean = new NotificationBean();
                    bean.setProgram_id(vv.get(0).toString());
                    bean.setProgram_name(vv.get(1).toString());
                    bean.setProgram_date(vv.get(2).toString());
                    bean.setProgram_location(vv.get(3).toString());
                    bean.setProgram_time(vv.get(4).toString());
                    bean.setProgram_description(vv.get(5).toString());
                    allInfos.add(bean);
                }
            }
            Gson gson = new Gson();

            infoall = gson.toJson(allInfos);

            System.out.println(infoall);
            if (infoall.isEmpty()) {
                infoall = "failed";
            }
            out.println(infoall);
        } else {
            System.out.println(infoall);
            out.println("failed");
        }
//        System.out.println(infoall);
    }

//getProgramNotification end
//NotificationDetails start
    if (key.equals("NotificationDetails")) {

        String uid = request.getParameter("uid");
        String data = "";
        String info = "";
        String id = "", dd = "";
        String carry = "initvalue", aid, child_status = "mother";

        //...get user month and aid start...
        String str = "SELECT `carring_month`,`aid` FROM `tbl_user` WHERE `uid`='" + uid + "'";
        Iterator it = con.getData(str).iterator();
        if (it.hasNext()) {
            Vector v1 = (Vector) it.next();
            carry = v1.get(0).toString();

            aid = v1.get(1).toString();
            System.out.println("carry,aid" + carry + aid);
        }
        //...get user month and aid end...

        //...check mother child start... 
        String str2 = "SELECT `dob` FROM `tbl_childregister` WHERE `mid`='" + uid + "'";
        Iterator it2 = con.getData(str2).iterator();
        if (it2.hasNext()) {
            Vector v2 = (Vector) it2.next();
            child_status = v2.get(0).toString();
            System.out.println("child_status" + child_status);
        }

        //...check mother child end... 
        String qry = "SELECT v.`vacci_name`,v.`usertype`,v.`vacci_age`,v.`details`,vn.`date`,vn.`location`,vn.`time`,vn.not_id FROM `tbl_vacci_notification` vn,`tbl_vaccination` v WHERE vn.`vid`=v.vid ";
        //System.out.println(qry);

        Iterator it1 = con.getData(qry).iterator();
        if (it1.hasNext()) {
            while (it1.hasNext()) {
                Vector v = (Vector) it1.next();
//              Checking viewed status or not
                String qry4check = "SELECT st_id FROM `tbl_notification_status` WHERE notf_id =" + v.get(7);
                Iterator itstatus = con.getData(qry4check).iterator();
                if (itstatus.hasNext()) {
                } else {
//                ........
                    LocalDate today = LocalDate.now();
                    String datee = v.get(4).toString();
                    LocalDate date = LocalDate.parse(datee);
                    Period period = Period.between(today, date);
                    //                .............
                    if ((period.getDays() >= 0) && (period.getMonths() >= 0) && (period.getYears() >= 0)) {

                        if (v.get(1).equals("Child") && !child_status.equals("mother")) {

                            LocalDate childdob = LocalDate.parse(child_status);
                            Period agediff = Period.between(childdob, today);
                            System.out.println("age diff" + agediff.getYears());
                            int vacciage = Integer.parseInt(v.get(2).toString());
                            if (agediff.getYears() == vacciage) {
                                id = id + v.get(5) + ":";
                                data = "\nPrgm Name  : " + v.get(0) + "\nLocation        :  " + v.get(1) + "\nDate               :  " + v.get(2) + "\nTime              :  " + v.get(3) + "\nDetails           :  " + v.get(4) + "\n";
                                dd += data + "#";
                            }
                        }
                        if (v.get(1).equals("Mother")) {
                            System.out.println("inside ifmother");

                            LocalDate carrymonth = LocalDate.parse(carry);
                            System.out.println("carrymonth" + carrymonth + "  today" + today);
                            Period monthdiff = Period.between(carrymonth, today);

                            int vaccimonth = Integer.parseInt(v.get(2).toString());
                            System.out.println("mo diff=" + monthdiff.getMonths() + "vaccimonth" + vaccimonth);
                            if (monthdiff.getMonths() == (vaccimonth)) {
                                id = id + v.get(5) + ":";
                                data = "\nPrgm Name  : " + v.get(0) + "\nLocation        :  " + v.get(1) + "\nDate               :  " + v.get(2) + "\nTime              :  " + v.get(3) + "\nDetails           :  " + v.get(4) + "\n";
                                //System.out.println(data);
                                dd += data + "#";
                            }
                        }

                    }
                }
            }
            info = id + "%" + dd;
            System.out.println(info);
            if (info.equals("%")) {
                info = "failed";
            }
            out.print(info);

        } else {
            out.print("failed");

        }

    }

//NotificationDetails end
//getProfile start
    if (key.equals("getProfile")) {

        String uid = request.getParameter("uid");
        String data = "";
        String qry = "SELECT * FROM `tbl_user` WHERE `uid`='" + uid + "'";
        System.out.println(qry);
        String monthcarry;
        Iterator it1 = con.getData(qry).iterator();
        if (it1.hasNext()) {

            Vector v = (Vector) it1.next();
            monthcarry = v.get(5).toString();
            if (!v.get(5).equals("")) {
                LocalDate today = LocalDate.now();
                LocalDate carrymonth = LocalDate.parse(v.get(5).toString());
                System.out.println("carrymonth" + carrymonth + "  today" + today);
                Period monthdiff = Period.between(carrymonth, today);
                int carrymon = monthdiff.getMonths();
                monthcarry = "" + carrymon;
            }

            data = v.get(0) + ":" + v.get(2) + ":" + v.get(3) + ":" + v.get(4) + ":" + monthcarry + ":" + v.get(6) + ":" + v.get(7) + ":" + v.get(8) + ":" + v.get(9);

            System.out.println(data);

            out.print(data);
        } else {
            out.print("failed");

        }
    }

//getProfile end
//getVacc_Details start
    if (key.equals("getVacc_Details")) {
        System.out.print("haii");
        String aid = request.getParameter("aid");
        String user_type = request.getParameter("userType");
        String qry1 = "SELECT v.`vid`,v.`vacci_name` FROM `tbl_vaccination` v,`regasha` a WHERE v.`usertype`='" + user_type + "' AND a.`PID`=v.`PID` AND a.`AID`='" + aid + "' ";
        System.out.println(qry1);
        System.out.print(qry1);
        String data = "", id = "", name = "";
        Iterator it1 = con.getData(qry1).iterator();
        if (it1.hasNext()) {
            while (it1.hasNext()) {
                Vector v = (Vector) it1.next();
                id += v.get(0) + ":";
                name += v.get(1) + ":";
            }
            data = id + "#" + name;
            out.print(data);
        } else {
            out.print("failed");
        }

    }
//getVacc_Detailsend

//addVaccNotifiaction start
    if (key.equals("addVaccNotifiaction")) {

        String aid = request.getParameter("aid");
        String vid = request.getParameter("vid");
        String date = request.getParameter("date");
        String location = request.getParameter("loacation");
        String time = request.getParameter("time");

        String qry = "INSERT INTO `tbl_vacci_notification` (`vid`,`aid`,`date`,`location`,`time`) VALUES('" + vid + "','" + aid + "','" + date + "','" + location + "','" + time + "')";

        System.out.println(qry);

        if (con.putData(qry) > 0) {

            out.print("successful");

        } else {
            out.print("failed");
        }
    }

//addVaccNotifiaction end
// Start UpdateNotiStatus
    if (key.equals("UpdateNotiStatus")) {

        String uid = request.getParameter("uid");
        String vid = request.getParameter("vid");
        String qry = "INSERT INTO `tbl_notification_status` (notf_id,uid,user_type) VALUES ('" + uid + "','" + vid + "','user')";

        System.out.println(qry);

        if (con.putData(qry) > 0) {

            out.print("successful");

        } else {
            out.print("failed");
        }
    }

//end UpdateNotiStatus


//strt check_email






//end check_email

if (key.equals("check_email")) {
        String id = "";
        String qry = "select UserId,Type from login where UserName='" + request.getParameter("email") + "' ";
        String username = request.getParameter("uname");
        System.out.println("qry=" + qry);
        Iterator it = con.getData(qry).iterator();
        if (it.hasNext()) {
            
            out.print("Existing");
        } else {
            System.out.println("else id=" + id);
            out.print("failed");
        }
    }



    if (key.equals("getphoneForgot")) {
        String id = "",qry1="";
        String qry = "select UserId,Type from login where UserName='" + request.getParameter("email") + "'";
        String username = request.getParameter("uname");
        System.out.println("qry=" + qry);
        Iterator it = con.getData(qry).iterator();
        if (it.hasNext()) {
            Vector v = (Vector) it.next();
            id = "" + v.get(1).toString();
            
            
            if(id.equals("asha"))
            {
                qry1="SELECT `Phone` FROM `regasha` WHERE `Email`='" + request.getParameter("email") + "'";
            }else  if(id.equals("user"))
            {
                qry1="SELECT `phone` FROM `tbl_user` WHERE `email`='" + request.getParameter("email") + "'";
            } else{
                
                 out.print("failed");
            }
                  
            
            
            Iterator it1 = con.getData(qry1).iterator();
        if (it1.hasNext()) {
            Vector v1 = (Vector) it1.next();
             String phone = "" + v1.get(0).toString();
             out.print(phone);
        }
            
            
           
        } else {
            System.out.println("else id=" + id);
            out.print("failed");
        }
    }


//changepass
    if (key.equals("changepass")) {
        String pass = request.getParameter("pass");
        String mail = request.getParameter("email");
        
        String qry = "UPDATE `login` SET `Password`='" + pass+ "' WHERE `UserName`='" + mail + "' " ;
        System.out.println(qry);

        if (con.putData(qry) > 0) {

            System.out.println("Deleted");
        } else {
            out.print("failed");
        }
    }
%>