package com.yj.springboot3web.config;


import org.springframework.boot.Banner;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * @author YangJian
 * @version 1.0.0
 * @title MyBanner
 * @create 2025/4/13 18:45
 * @description <TODO description class purpose>
 */
public class MyBanner implements Banner {

    private static final String[] BANNER = new String[]{"", "  .   ____          _            __ _ _", " /\\\\ / ___'_ __ _ _(_)_ __  __ _ \\ \\ \\ \\", "( ( )\\___ | '_ | '_| | '_ \\/ _` | \\ \\ \\ \\", " \\\\/  ___)| |_)| | | | | || (_| |  ) ) ) )", "  '  |____| .__|_| |_|_| |_\\__, | / / / /", " =========|_|==============|___/=/_/_/_/"};

    public MyBanner() {
    }

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        String[] bannerArray = BANNER;
        int bannerLength = bannerArray.length;
        for(int i = 0; i < bannerLength; ++i) {
            String line = bannerArray[i];
            out.println(line);
        }
        out.println(AnsiOutput.toString(new Object[]{AnsiColor.GREEN, " :: Spring Boot :: ", AnsiColor.DEFAULT,  AnsiStyle.FAINT}));
        out.println();
    }
}

