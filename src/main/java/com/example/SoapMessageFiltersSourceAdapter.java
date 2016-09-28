package com.example;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import org.littleshoot.proxy.HttpFilters;
import org.littleshoot.proxy.HttpFiltersAdapter;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class SoapMessageFiltersSourceAdapter extends HttpFiltersSourceAdapter {

    private static final int MAX_REQUEST_BUFFER_SIZE = 1024 * 1024;
    private static final int MAX_RESPONSE_BUFFER_SIZE = 1024 * 1024 * 2;

    @Autowired
    private SoapMessageHandler messageHandler;

    @Override
    public int getMaximumRequestBufferSizeInBytes() {
        return MAX_REQUEST_BUFFER_SIZE;
    }

    @Override
    public int getMaximumResponseBufferSizeInBytes() {
        return MAX_RESPONSE_BUFFER_SIZE;
    }

    public HttpFilters filterRequest(HttpRequest originalRequest, ChannelHandlerContext ctx) {
        return new HttpFiltersAdapter(originalRequest) {
            @Override
            public HttpResponse clientToProxyRequest(HttpObject httpObject) {
                System.out.println("==================== Start ================= \nclientToProxyRequest");
                messageHandler.extractAndLogMessageContent(httpObject);

                return messageHandler.findMatch(httpObject);
            }

            @Override
            public HttpObject serverToProxyResponse(HttpObject httpObject) {
                System.out.println("serverToProxyResponse");
                messageHandler.extractAndLogMessageContent(httpObject);
                return httpObject;
            }

            @Override
            public HttpResponse proxyToServerRequest(HttpObject httpObject) {
                System.out.println("proxyToServerRequest");
                return super.proxyToServerRequest(httpObject);
            }

            @Override
            public void proxyToServerRequestSending() {
                System.out.println("proxyToServerRequestSending");
                super.proxyToServerRequestSending();
            }

            @Override
            public void proxyToServerRequestSent() {
                System.out.println("proxyToServerRequestSent");
                super.proxyToServerRequestSent();
            }

            @Override
            public void serverToProxyResponseTimedOut() {
                System.out.println("serverToProxyResponseTimedOut");
                super.serverToProxyResponseTimedOut();
            }

            @Override
            public void serverToProxyResponseReceiving() {
                System.out.println("serverToProxyResponseReceiving");
                super.serverToProxyResponseReceiving();
            }

            @Override
            public void serverToProxyResponseReceived() {
                System.out.println("serverToProxyResponseReceived");
                super.serverToProxyResponseReceived();
            }

            @Override
            public HttpObject proxyToClientResponse(HttpObject httpObject) {
                System.out.println("proxyToClientResponse");
                return super.proxyToClientResponse(httpObject);
            }

            @Override
            public void proxyToServerConnectionQueued() {
                System.out.println("proxyToServerConnectionQueued");
                super.proxyToServerConnectionQueued();
            }

            @Override
            public InetSocketAddress proxyToServerResolutionStarted(String resolvingServerHostAndPort) {
                System.out.println("proxyToServerResolutionStarted");
                return super.proxyToServerResolutionStarted(resolvingServerHostAndPort);
            }

            @Override
            public void proxyToServerResolutionFailed(String hostAndPort) {
                System.out.println("proxyToServerResolutionFailed");
                super.proxyToServerResolutionFailed(hostAndPort);
            }

            @Override
            public void proxyToServerResolutionSucceeded(String serverHostAndPort, InetSocketAddress resolvedRemoteAddress) {
                System.out.println("proxyToServerResolutionSucceeded");
                super.proxyToServerResolutionSucceeded(serverHostAndPort, resolvedRemoteAddress);
            }

            @Override
            public void proxyToServerConnectionStarted() {
                System.out.println("proxyToServerConnectionStarted");
                super.proxyToServerConnectionStarted();
            }

            @Override
            public void proxyToServerConnectionSSLHandshakeStarted() {
                System.out.println("proxyToServerConnectionSSLHandshakeStarted");
                super.proxyToServerConnectionSSLHandshakeStarted();
            }

            @Override
            public void proxyToServerConnectionFailed() {
                System.out.println("proxyToServerConnectionFailed");
                super.proxyToServerConnectionFailed();
            }

            @Override
            public void proxyToServerConnectionSucceeded(ChannelHandlerContext serverCtx) {
                System.out.println("proxyToServerConnectionSucceeded");
                super.proxyToServerConnectionSucceeded(serverCtx);
            }
        };
    }

}
