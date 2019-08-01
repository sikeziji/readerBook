package com.example.readerbook;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import novel.spider.Enum.NovelSiteEnum;
import novel.spider.configuration.configuration;
import novel.spider.entitys.Chapter;
import novel.spider.entitys.Novel;
import novel.spider.impl.chapter.BxwxChapterSpider;
import novel.spider.impl.chapter.DefaultChapterDetailSpider;
import novel.spider.impl.chapter.DefaultChapterSpider;
import novel.spider.impl.download.NovelDownload;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.interfaces.INovelDownload;
import novel.spider.interfaces.INovelSpider;
import novel.spider.util.NovelSpiderFactory;
import novel.spider.util.NovelSpiderUtil;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    /**
     * 测试拿到笔下文学的章节列表
     */
    @Test
    public void testGetsChapter3() {
        IChapterSpider spider = new BxwxChapterSpider();
        List<Chapter> chapters = spider.getsChapter("https://www.bxwx9.org/b/5/5740/");
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
    }


    /**
     * 测试拿到顶点小说的章节列表
     */
    @Test
    public void testGetsChapter() throws Exception {
        IChapterSpider spider = new DefaultChapterSpider();
        List<Chapter> chapters = spider.getsChapter("http://www.23us.so/files/article/html/1/1969/index.html");
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
    }

    /**
     * 测试拿到顶点文学网的爬取方式
     */
    @Test
    public void testGetSiteContext() {
        System.out.println(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("https://www.booktxt.net/2_2096/")));
    }


    /**
     * 爬取顶点小说的完美世界章节内容
     */
    @Test
    public void testGetChapterDetail() {
        IChapterDetailSpider spider = new DefaultChapterDetailSpider();
        System.out.println(spider.getChapterDetail("http://www.23us.so/files/article/html/19/19198/10917119.html"));
    }

    @Test
    public void testOk() {
        String s = "'&nbsp;&nbsp;&nbsp;&nbsp;你努力上下晃动头部，表示确定。<br /> <br /> &nbsp;&nbsp;&nbsp;&nbsp;“好，那你去吧。";
        s = s.replace("<br /> ", "\n");
        System.out.println(s);
    }

    /**
     * 爬取笔下文学的元尊章节内容
     */
    @Test
    public void testGetChapterDetail2() {
        IChapterDetailSpider spider = new DefaultChapterDetailSpider();
        System.out.println(spider.getChapterDetail("https://www.bxwx9.org/b/5/5740/41249369.html").getContent());
    }

    @Test
    public void testDownload() {
        INovelDownload download = new NovelDownload();
        configuration config = new configuration();
        //保存小说的路径
        config.setPath("D:/小说");
        //每个线程开始进行下载时所用的数量
        config.setSize(100);
        //线程出错重试的次数
        config.setTryTimes(10);
//        download.download("http://www.23us.so/files/article/html/1/1969/", config);
        System.out.println("下载好了，文件保存在：" + download.download("https://www.23us.so/files/article/html/0/419/index.html", config) + "这里，赶紧去看看吧！");
    }


    @Test
    public void testSubList() {
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ints.add(i * i);
        }
        System.out.println(ints);
        //[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        //0-4
        System.out.println(ints.subList(0, ints.size()));
    }

    @Test
    public void testMultiFileMerge() {
        NovelSpiderUtil.multiFileMerge("D:/小说", null, true, "测试");
    }

    @Test
    public void testBxwxGetsNovels() {
        INovelSpider spider = NovelSpiderFactory.getNovelSpider("http://www.bxwx9.org");
        List<Novel> novels = spider.getsNovel("https://www.bxwx9.org/modules/article/index.php");
        for (Novel novel : novels) {
            System.out.println(novel);
        }
    }

    @Test
    public void testDdxsGetsNovels() {
        INovelSpider spider = NovelSpiderFactory.getNovelSpider("https://www.23us.so/top/allvote_1.html");
        List<Novel> novels = spider.getsNovel("https://www.23us.so/top/allvote_1.html");
        for (Novel novel : novels) {
            System.out.println(novel);
        }
    }

    @Test
    public void test36Ker() {

    }


    @Test
    public void Testasdfasdf() {
        //获取URL对应的HTML内容
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //层层定位到要解析的内容，可以发现包含多个li标签
        Elements elements = doc.select("div[id=course]").select("li");
        //遍历每一个li节点
        for (org.jsoup.nodes.Element ele : elements) {
            String title = ele.select("a").text();  //.text()为解析标签中的文本内容
            String course_url = ele.select("a").attr("href");  //.attr(String)表示获取标签内某一属性的内容
            System.out.println("课程的标题为:" + title + "\t对应的URL为" + course_url);
        }
    }


}