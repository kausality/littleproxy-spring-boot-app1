package com.example;

import com.google.common.primitives.Bytes;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
class SoapMessageHandler {
    private final static Logger LOG = Logger.getLogger(SoapMessageFiltersSourceAdapter.class);

    public HttpResponse findMatch(HttpObject requestObject) {
        String cachedRS = "";
        try {
            cachedRS = new String(Files.readAllBytes(Paths.get("src/main/resources/cachedRS.xml")));
        } catch (IOException e) {
            return null;
        }

        ByteBuf cachedBuf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(cachedRS, CharsetUtil.UTF_8));
        HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, cachedBuf);
        HttpHeaders.setContentLength(response, cachedBuf.readableBytes());
        HttpHeaders.setHeader(response, HttpHeaders.Names.CONTENT_TYPE, "text/html");

        return response;
    }

    public void extractAndLogMessageContent(HttpObject httpObject) {
        String message = extractMessageContent(httpObject);
        LOG.info(message);
    }

    private String extractMessageContent(HttpObject httpObject) {
        List<Byte> bytes = new ArrayList<>();
        HttpContent httpContent = (HttpContent) httpObject;
        ByteBuf buf = httpContent.content();
        byte[] buffer = new byte[buf.readableBytes()];
        if (buf.readableBytes() > 0) {
            int readerIndex = buf.readerIndex();
            buf.getBytes(readerIndex, buffer);
        }
        for (byte b : buffer) {
            bytes.add(b);
        }

        return new String(Bytes.toArray(bytes));
    }

}
