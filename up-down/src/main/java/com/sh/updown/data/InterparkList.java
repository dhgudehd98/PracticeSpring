package com.sh.updown.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InterparkList {
    List<String> interparkList = new ArrayList<>();
    LocalDate now = LocalDate.now();
    LocalDate start = now.plusWeeks(1);
    LocalDate end = start.plusWeeks(1);
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public List<String> list() {
            String interparkJeju = "https://travel.interpark.com/tour/search?category=domestic&q=%EC%A0%9C%EC%A3%BC%EB%8F%84&domain=t& "
                    + "startDate=" + start.format(dateFormatter)
                    +"&endDate=" + end.format(dateFormatter)
                    + "&departure=%EC%B6%9C%EB%B0%9C%EC%A7%80+%EC%A0%84%EC%B2%B4";

            String interparkUlleungdo ="https://travel.interpark.com/tour/search?category=domestic&q=울릉도&domain=t& "
                    + "startDate=" + start.format(dateFormatter)
                    +"&endDate=" + end.format(dateFormatter)
                    + "&departure=%EC%B6%9C%EB%B0%9C%EC%A7%80+%EC%A0%84%EC%B2%B4";

            String interparkGangwondo = "https://travel.interpark.com/tour/search?category=domestic&q=강원&domain=t& "
                    + "startDate=" + start.format(dateFormatter)
                    +"&endDate=" + end.format(dateFormatter)
                    + "&departure=%EC%B6%9C%EB%B0%9C%EC%A7%80+%EC%A0%84%EC%B2%B4";

            String interparkJeollado = "https://travel.interpark.com/tour/search?category=domestic&q=전라&domain=t& "
                    + "startDate=" + start.format(dateFormatter)
                    +"&endDate=" + end.format(dateFormatter)
                    + "&departure=%EC%B6%9C%EB%B0%9C%EC%A7%80+%EC%A0%84%EC%B2%B4";

            String interparkBusan = "https://travel.interpark.com/tour/search?category=domestic&q=부산&domain=t& "
                    + "startDate=" + start.format(dateFormatter)
                    +"&endDate=" + end.format(dateFormatter)
                    + "&departure=%EC%B6%9C%EB%B0%9C%EC%A7%80+%EC%A0%84%EC%B2%B4";

            String interparkGeoje = "https://travel.interpark.com/tour/search?category=domestic&q=거제&domain=t& "
                    + "startDate=" + start.format(dateFormatter)
                    +"&endDate=" + end.format(dateFormatter)
                    + "&departure=%EC%B6%9C%EB%B0%9C%EC%A7%80+%EC%A0%84%EC%B2%B4";

            String interparkNamhae = "https://travel.interpark.com/tour/search?category=domestic&q=남해4&domain=t& "
                    + "startDate=" + start.format(dateFormatter)
                    +"&endDate=" + end.format(dateFormatter)
                    + "&departure=%EC%B6%9C%EB%B0%9C%EC%A7%80+%EC%A0%84%EC%B2%B4";

            String interparkTongyeong = "https://travel.interpark.com/tour/search?category=domestic&q=통영&domain=t& "
                    + "startDate=" + start.format(dateFormatter)
                    +"&endDate=" + end.format(dateFormatter)
                    + "&departure=%EC%B6%9C%EB%B0%9C%EC%A7%80+%EC%A0%84%EC%B2%B4";

            String interparkGyeongju = "https://travel.interpark.com/tour/search?category=domestic&q=경주&domain=t& "
                    + "startDate=" + start.format(dateFormatter)
                    +"&endDate=" + end.format(dateFormatter)
                    + "&departure=%EC%B6%9C%EB%B0%9C%EC%A7%80+%EC%A0%84%EC%B2%B4";

            String interparkYeosu = "https://travel.interpark.com/tour/search?category=domestic&q=여수&domain=t& "
                    + "startDate=" + start.format(dateFormatter)
                    +"&endDate=" + end.format(dateFormatter)
                    + "&departure=%EC%B6%9C%EB%B0%9C%EC%A7%80+%EC%A0%84%EC%B2%B4";

            interparkList.add(interparkJeju);
            interparkList.add(interparkUlleungdo);
            interparkList.add(interparkGangwondo);
            interparkList.add(interparkJeollado);
            interparkList.add(interparkBusan);
            interparkList.add(interparkGeoje);
            interparkList.add(interparkNamhae);
            interparkList.add(interparkTongyeong);
            interparkList.add(interparkGyeongju);
            interparkList.add(interparkYeosu);


        return interparkList;
    }
}