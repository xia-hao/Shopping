package com.shopping;

import com.shopping.domain.ProductCategory;
import com.shopping.domain.User;
import com.shopping.service.*;
import com.shopping.utils.ImageUtil;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.List;

@SpringBootTest
class ShoppingApplicationTests {

    @Resource
    private ProductCategoryService productCategoryService;

    @Resource
    private SlideShowService slideShowService;

    @Resource
    private DistrictService districtService;

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
        List<ProductCategory> list1 = productCategoryService.selectByParentId(null);
        for (ProductCategory productCategory1 : list1) {
            System.out.println("商品一级菜单：" + productCategory1.getName());
            List<ProductCategory> list2 = productCategoryService.selectByParentId(productCategory1.getId());
            for (ProductCategory productCategory2 : list2) {
                System.out.println("商品二级菜单：" + productCategory2.getName());
                List<ProductCategory> list3 = productCategoryService.selectByParentId(productCategory2.getId());
                for (ProductCategory productCategory3 : list3) {
                    System.out.println("商品三级菜单：" + productCategory3.getName());
                }
            }
        }
    }

    @Test
    void test() {
        URL url = ClassLoader.getSystemClassLoader().getResource("./");
        String path = url.getPath().substring(0,url.getPath().indexOf("shopping"))
                +File.separator+"shopping-ui"+File.separator+"static"+File.separator+"images"; // 图片存放路径
        System.out.println(path);
    }

    @Test
    void test1() {
        User user = userService.getUserByLoginNameOrMobile("admin");
        System.out.println(user.getUsername());
    }

    @Test
    void test2() {
        ReadableByteChannel readableByteChannel = null;
        File file;
        URL url;
        FileOutputStream fileOutputStream = null;
        FileChannel fileChannel = null;
        try {
            url = new URL("https://cangyan-app-user-1251043689.cos.ap-shanghai.myqcloud.com/202005232118034252.jpeg");
            //首先从 URL stream 中创建一个 ReadableByteChannel 来读取网络文件
            readableByteChannel = Channels.newChannel(url.openStream());
            String fileName = System.currentTimeMillis() + url.getPath().substring(url.getPath().lastIndexOf("."));
            String path = System.getProperty("user.dir")+"\\images\\"+ fileName;
            file = new File(path);
            if (!file.getParentFile().exists() && !file.getParentFile().isDirectory()) {
                file.getParentFile().mkdirs();
            }
            //通过 ReadableByteChannel 读取到的字节会流动到一个 FileChannel 中，然后再关联一个本地文件进行下载操作
            fileOutputStream = new FileOutputStream(file);
            fileChannel = fileOutputStream.getChannel();
            //最后用 transferFrom()方法就可以把 ReadableByteChannel 获取到的字节写入本地文件
            fileChannel.transferFrom(readableByteChannel,0,Long.MAX_VALUE);

            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(), ContentType.IMAGE_JPEG.toString(), input);
            String abbreviationPath = ImageUtil.resFilePath(multipartFile);
            File file1 = new File(abbreviationPath);
            fileOutputStream.close();
            input.close();
            if (file1.exists()) {
                file1.delete();
            }
            if (file.exists()) {
                file.delete();
            }
            System.out.println(file);
            System.out.println(abbreviationPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
