package com.github.kongchen.swagger.docgen.mavenplugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: kongchen
 * Date: 6/4/13
 */
public class ApiDocumentMojoTest {

    private List<ApiSource> apiSources = new ArrayList<ApiSource>();
    ApiDocumentMojo mojo = new ApiDocumentMojo();

    @BeforeClass
    private void prepare(){

        ApiSource apiSource;
        apiSource = new ApiSource();
        apiSource.setApiVersion("1.0");
        apiSource.setBasePath("http://example.com");
        apiSource.setLocations("sample.api");
        apiSource.setOutputPath("temp.html");
        apiSource.setOutputTemplate("strapdown.html.mustache");
        apiSource.setWithFormatSuffix(false);
        apiSource.setSwaggerDirectory("apidocsf");

        apiSources.add(apiSource);
        mojo.setApiSources(apiSources);

    }
    @Test(enabled = false)
    public void testExecute() throws Exception {
        mojo.execute();
        FileInputStream testOutputIs = new FileInputStream(new File("temp.html"));
        InputStream expectIs = this.getClass().getResourceAsStream("/sample.html");
        while (true) {
            int expect = expectIs.read();
            int actual = testOutputIs.read();

            Assert.assertEquals(expect, actual);
            if (expect == -1) {
                break;
            }
        }
    }

}
