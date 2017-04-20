package com.api_test;


import android.util.Log;

/**
 * Created by NEDUsoftware on 2017/4/19.
 */

public class JsonBean {
    private String resultcode;
    private String reason;
    private Result_1 result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Result_1 getResult() {
        return result;
    }

    public void setResult(Result_1 result) {
        this.result = result;
    }

    public static class Result_1{
        private SK sk;
        private Today today;
        private String wind;
        private String week;
        private String city;
        private String date_y;

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDate_y() {
            return date_y;
        }

        public void setDate_y(String date_y) {
            this.date_y = date_y;
        }

        public SK getSk() {
            return sk;
        }

        public void setSk(SK sk) {
            this.sk = sk;
        }

        public Today getToday() {
            return today;
        }

        public void setToday(Today today) {
            this.today = today;
        }

        public static class SK{
            private String temp;
            private String wind_direction;
            private String wind_strength;
            private String humidity;
            private String time;

            public String getTemp() {
                return temp;
            }

            public void setTemp(String temp) {
                this.temp = temp;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public String getWind_strength() {
                return wind_strength;
            }

            public void setWind_strength(String wind_strength) {
                this.wind_strength = wind_strength;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            @Override
            public String toString() {
                Log.i("result", "toString: "+temp);
                return "当前天气：\n当前气温："+temp+"\n当前湿度："+humidity+"\n当前时间："+time;
            }
        }
        public static class Today{
            private String temperature;
            private String weather;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }
        }

        @Override
        public String toString() {
            Log.i("as", "Wind: "+wind);
            return "fengxiang:"+wind;
        }
    }

    @Override
    public String toString() {
        Result_1 r=new Result_1();
        return r.toString();
    }
}
