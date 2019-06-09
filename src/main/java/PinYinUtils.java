import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.Scanner;

/**
 * Author:   supeng
 * Date:     2019-05-27 11:41
 * Description:  汉字转拼音
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class PinYinUtils {

    /**
     * 返回汉字首字母
     */

    public static String getHanziInitials(String hanzi) {
        String result = null;
        if (null != hanzi && !"".equals(hanzi)) {
            char[] hanzichar = hanzi.toCharArray();
            StringBuffer stringBuffer = new StringBuffer();
            for (char ch : hanzichar) {
                String[] str = PinyinHelper.toHanyuPinyinStringArray(ch);
                if (null != str) {
                    stringBuffer.append(str[0].charAt(0));
                }
            }
            if (stringBuffer.length() > 0) {
                result = stringBuffer.toString().toUpperCase();
            }
        }
        return result;

    }

    public static String getHanziPinYin(String hanzi) {

        String result = null;
        if (null != hanzi && !"".equals(hanzi)) {
            char[] charArray = hanzi.toCharArray();
            StringBuffer stringBuffer = new StringBuffer();
            for (char ch : charArray) {
                String[] str = PinyinHelper.toHanyuPinyinStringArray(ch);
                if (null != str) {
                    stringBuffer.append(str[0].replaceAll("\\d", ""));
                }
            }
            if (stringBuffer.length() > 0) {
                result = stringBuffer.toString();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(PinYinUtils.getHanziInitials("袁素芳"));

        /*
        String tbName1 = "allStudent";
        String tbComment1 = "全体在学学生";
        String tbHead1 = "学号\t姓名\t性别\t地址\tEMAIL\t邮编\t学生来源\t工作单位\t班级\t考生号\t注册号\t手机\t电话\t出生日期\t学生标签\t学习中心名称\t学习中心所在省\t专业名称\t入学日期\t注册日期\t学习形式\t培养层次\t培养层次属性\t一学历学校代码\t一学历学校名称\t一学历毕业年月\t一学历证书编号\t办学类型\t办学类别\t学制\t批准文号\t毕业日期\t校长姓名\t网院院长姓名\t身份证号\t考点名称\t民族\t专科获取时间\t学籍状态\t学籍状态说明\t政治面貌\t备注\n";
        System.out.println(dealHanziStr(tbName1,tbComment1,  tbHead1));
        System.out.println();


        String tbName2 = "finishStudent";
        String tbComment2 = "预毕业学生列表";
        String tbHead2 = "考生号\t注册号\t学号\t姓名\t中心名称\t班级\t专业名称\t必修课应修学分\t必修课欠学分\t选修课应修学分\t选修课欠学分\t选修课已选学分\t毕业设计成绩\t英语统考\t计算机统考\t应交总额\t欠交学费\t手机\t学生标签\t毕业登记状态\t城市\n";
        System.out.println(dealHanziStr(tbName2, tbComment2, tbHead2));


        String tbName1 = "examStudent20190527";
        String tbComment1 = "全体约考信息";
        String tbHead1 = "具体考试计划名称\t学习中心\t考点\t考试科目\t考试场次\t考试时间\t考核形式\t学号\t姓名\t手机\t学生标签\t班级\t专业\t培养层次\t身份证号\n";
        System.out.println(dealHanziStr(tbName1,tbComment1,  tbHead1));
        System.out.println();




        String tbName1 = "allStudent3";
        String tbComment1 = "全体在学课程生";
        String tbHead1 = "学号\t姓名\t性别\t地址\tEMAIL\t邮编\t班级\t考生号\t注册号\t手机\t电话\t出生日期\t学生标签\t学习中心名称\t学习中心所在省\t专业名称\t入学日期\t注册日期\t学习形式\t培养层次\t培养层次属性\t一学历学校代码\t一学历学校名称\t一学历毕业年月\t一学历证书编号\t办学类型\t办学类别\t学制\t批准文号\t毕业日期\t校长姓名\t网院院长姓名\t身份证号\t考点名称\t民族\t专科获取时间\t学籍状态\t学籍状态说明\t政治面貌\t备注\n";
        System.out.println(dealHanziStr(tbName1,tbComment1,  tbHead1));
        System.out.println();

         */

        String tbName1 = "examStudent20190603";
        String tbComment1 = "哈理工总评成绩";
        String tbHead1 = "学习中心\t专业\t层次\t年级\t学号\t姓名\t课程名称\t试卷号\t生成时间\t总评成绩\t考试成绩\t平时成绩平均值\t平时成绩1\t平时成绩2\t平时成绩3\t平时成绩4\t平时成绩5\n";
        System.out.println(dealHanziStr(tbName1,tbComment1,  tbHead1));
        System.out.println();

    }


    public static String dealHanziStr(String tableName, String tbComment,  String source) {

        source = source.replace("\\n", "");

        String[] hanziStr = null;

        String result = null;




        String createStart = "CREATE TABLE `";

        String createEnd = ") ";

        tbComment = "comment = "+"'"+ tbComment + "';";

        StringBuffer stringBuffer = new StringBuffer(createStart+ tableName + "` (");

        if (null != source && !"".equals(source)) {
            hanziStr = source.split("\\t");

            String end = ")";

            for (String str : hanziStr) {
                str = str.replace("\n", "");
                stringBuffer.append("`"+PinYinUtils.getHanziPinYin(str)+"` varchar(255) " + "comment '"+ str + "',"+"\n");
            }
        }

        if (stringBuffer.length() > 0) {
            result = stringBuffer.substring(0,  stringBuffer.lastIndexOf(","));

            result = result +"\n" +createEnd+ tbComment;
        }

        return result;

    }


}