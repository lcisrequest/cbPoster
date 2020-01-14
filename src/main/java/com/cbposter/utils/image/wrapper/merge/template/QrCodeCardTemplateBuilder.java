package com.cbposter.utils.image.wrapper.merge.template;


import com.cbposter.utils.base.ImageOperateUtil;
import com.cbposter.utils.image.util.FontUtil;
import com.cbposter.utils.image.wrapper.create.ImgCreateOptions;
import com.cbposter.utils.image.wrapper.merge.cell.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yihui on 2017/10/13.
 */
public class QrCodeCardTemplateBuilder {


    public static List<IMergeCell> build(BufferedImage image1,
                                         String name,
                                         List<String> desc,
                                         BufferedImage qrcode,
                                         String title) {
        List<IMergeCell> list = new ArrayList<>();

        //写入背景色
        list.add(buildBg());
        //写入第一个图片
        list.add(buildImage(image1));
        //写入文字
        list.addAll(buildTextInfo(name, desc));
        //写入线条
        list.add(buildLine());
        //写入第二个图片
        list.add(buildImage2(qrcode));
        //写入第二部分文字
        list.add(buildImageInfo("标 题 2"));
        //写入图片大小
        list.add(buildRectInfo());
        //写入标题
        list.addAll(buildTitle(title));
        return list;
    }

    /**
     * 设置背景色(宽，高，颜色，x，y)
     *
     * @return
     */
    private static RectFillCell buildBg() {
        //创建矩形框
        RectFillCell rectFillCell = RectFillCell.builder()
                //设置宽高和xy
                .w(QrCodeCardTemplate.w)
                .h(QrCodeCardTemplate.h)
                .x(0)
                .y(0)
                //设置颜色
                .color(QrCodeCardTemplate.bg_color)
                .build();
        return rectFillCell;
    }


    /**
     * 设置图片(图片内容，x位置，y位置，宽，高)
     *
     * @param logo
     * @return
     */
    private static ImgCell buildImage(BufferedImage logo) {
        logo = ImageOperateUtil.makeRoundImg(logo, false, null);
        return ImgCell.builder()
                //写入图片
                .img(logo)
                //写入x的位置
                .x(((QrCodeCardTemplate.text_size - QrCodeCardTemplate.text_logo_size) >>> 1) + QrCodeCardTemplate.text_x)
                //写入y的位置
                .y(QrCodeCardTemplate.text_y)
                //写入宽高
                .w(QrCodeCardTemplate.text_logo_size)
                .h(QrCodeCardTemplate.text_logo_size)
                .build();
    }


    /**
     * 设置文字内容(具体内容，字体，字体位置，文本颜色，文本开始x，y，结束x，y，垂直或水平，对齐方式)
     *
     * @param name
     * @param desc
     * @return
     */
    private static List<TextCell> buildTextInfo(String name, List<String> desc) {
        // 文案
        //获取字体
        FontMetrics nameFontMetrics = FontUtil.getFontMetric(QrCodeCardTemplate.text_nameFont);
        //字体位置
        int nameY = QrCodeCardTemplate.text_y + QrCodeCardTemplate.text_logo_size
                + QrCodeCardTemplate.text_line_space
                + nameFontMetrics.getHeight()
                + nameFontMetrics.getDescent();

        //文本单位
        TextCell nameCell = new TextCell();
        nameCell.setFont(QrCodeCardTemplate.text_nameFont);
        nameCell.setColor(QrCodeCardTemplate.text_nameFont_color);
        nameCell.setStartX(QrCodeCardTemplate.text_x);
        nameCell.setStartY(nameY);
        nameCell.setEndX(QrCodeCardTemplate.text_x + QrCodeCardTemplate.text_size);
        nameCell.setEndY(nameY + nameFontMetrics.getHeight());
        nameCell.addText(name);
        //垂直或水平
        nameCell.setDrawStyle(ImgCreateOptions.DrawStyle.HORIZONTAL);
        //对齐方式
        nameCell.setAlignStyle(ImgCreateOptions.AlignStyle.CENTER);


        // 说明文案
        FontMetrics descFontMetrics = FontUtil.getFontMetric(QrCodeCardTemplate.text_descFont);
        int descY = nameY + nameFontMetrics.getHeight() + QrCodeCardTemplate.text_line_space;
        TextCell descCell = new TextCell();
        descCell.setFont(QrCodeCardTemplate.text_descFont);
        descCell.setColor(QrCodeCardTemplate.text_descFont_color);
        descCell.setStartX(QrCodeCardTemplate.text_x);
        descCell.setStartY(descY);
        descCell.setEndX(QrCodeCardTemplate.text_x + QrCodeCardTemplate.text_size);
        descCell.setEndY(descY + desc.size() * descFontMetrics.getHeight());
        // 单行超过限制的需要分割
        descCell.setTexts(desc);
        descCell.setDrawStyle(ImgCreateOptions.DrawStyle.HORIZONTAL);
        descCell.setAlignStyle(ImgCreateOptions.AlignStyle.CENTER);


        return Arrays.asList(nameCell, descCell);
    }


    /**
     * 创建线条（线条的宽，高，颜色）
     *
     * @return
     */
    private static LineCell buildLine() {
        // line
        //创建线条
        return LineCell.builder()
                //设置线条的x,y,宽高
                .x1(QrCodeCardTemplate.line_x)
                .y1(QrCodeCardTemplate.line_y + QrCodeCardTemplate.line_h)
                .x2(QrCodeCardTemplate.line_x + QrCodeCardTemplate.line_w)
                .y2(QrCodeCardTemplate.line_y)
                //颜色
                .color(QrCodeCardTemplate.line_color)
                .build();
    }


    private static ImgCell buildImage2(BufferedImage qrcode) {

        int qrCodeX = QrCodeCardTemplate.qrcode_x + ((QrCodeCardTemplate.qrcode_info_w - QrCodeCardTemplate.qrcode_size) >>> 1);

        return ImgCell.builder()
                .img(qrcode)
                .x(qrCodeX)
                .y(QrCodeCardTemplate.qrcode_y)
                .w(QrCodeCardTemplate.qrcode_size)
                .h(QrCodeCardTemplate.qrcode_size)
                .build();
    }


    private static TextCell buildImageInfo(String textInfo) {
        Font font = QrCodeCardTemplate.qrcode_info_font;
        FontMetrics fontMetrics = FontUtil.getFontMetric(font);
        int startY = QrCodeCardTemplate.qrcode_y
                + QrCodeCardTemplate.qrcode_size
                + QrCodeCardTemplate.qrcode_info_padding
                + fontMetrics.getHeight();
        //文本单元
        TextCell textCell = new TextCell();
        textCell.setStartX(QrCodeCardTemplate.qrcode_x);
        textCell.setEndX(QrCodeCardTemplate.w - QrCodeCardTemplate.border_space);
        textCell.setStartY(startY);
        textCell.setEndY(startY + fontMetrics.getHeight());
        textCell.setFont(font);
        textCell.setColor(QrCodeCardTemplate.qrcode_info_color);
        textCell.setAlignStyle(ImgCreateOptions.AlignStyle.CENTER);
        textCell.addText(textInfo);
        return textCell;
    }


    /**
     * 设置边界（颜色，x，y，宽，高）
     *
     * @return
     */
    private static RectCell buildRectInfo() {
        RectCell rectCell = new RectCell();
        rectCell.setColor(Color.LIGHT_GRAY);
        rectCell.setX(QrCodeCardTemplate.border_space >>> 1);
        rectCell.setY(QrCodeCardTemplate.border_space >>> 1);
        rectCell.setW(QrCodeCardTemplate.w - QrCodeCardTemplate.border_space);
        rectCell.setH(QrCodeCardTemplate.h - QrCodeCardTemplate.border_space);

        return rectCell;
    }


    /**
     * 设置标题（字体相关属性，宽度，位置，矩形框的宽高xy，字体颜色）
     *
     * @param title
     * @return
     */
    private static List<IMergeCell> buildTitle(String title) {
        //字体
        Font titleFont = QrCodeCardTemplate.title_font;
        FontMetrics metrics = FontUtil.getFontMetric(titleFont);


        int w = QrCodeCardTemplate.w;
        int spacing = QrCodeCardTemplate.title_padding;


        int tw = metrics.stringWidth(title);

        //填充矩形框
        RectFillCell rectFillCell = RectFillCell.builder()
                .x((w - tw - metrics.getHeight() - metrics.getHeight()) >>> 1)
                .y(spacing >>> 1)
                .w(tw + metrics.getHeight() * 2)
                .h(spacing)
                .font(titleFont)
                .color(QrCodeCardTemplate.title_font_bg_color)
                .build();


        TextCell textCell = new TextCell();
        textCell.setStartX(0);
        textCell.setEndX(w);
        textCell.setStartY(spacing + titleFont.getSize() / 2 - metrics.getDescent());
        textCell.setEndY(textCell.getStartY());
        textCell.setAlignStyle(ImgCreateOptions.AlignStyle.CENTER);
        textCell.setDrawStyle(ImgCreateOptions.DrawStyle.HORIZONTAL);
        textCell.addText(title);
        textCell.setFont(titleFont);
        textCell.setColor(QrCodeCardTemplate.title_font_color);


        return Arrays.asList(rectFillCell, textCell);
    }
}
