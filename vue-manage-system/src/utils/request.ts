import axios, { AxiosInstance, AxiosError, AxiosResponse, InternalAxiosRequestConfig } from 'axios';

const service: AxiosInstance = axios.create({
    // baseURL: 'http://192.168.180.132:5000',
    timeout: 5000,
});

service.interceptors.request.use(
    (config: InternalAxiosRequestConfig) => {
        return config;
    },
    (error: AxiosError) => {
        console.error('Request error:', error);
        return Promise.reject(error);
    }
);

service.interceptors.response.use(
    (response: AxiosResponse) => {
        // 如果状态码为200，正常返回response
        if (response.status === 200) {
            return response;
        } else {
            // 非200状态码，拒绝promise并附带服务器返回的response
            return Promise.reject(response);
        }
    },
    (error: AxiosError) => {
        // 如果响应是错误的，检查并返回详细的错误信息
        if (error.response) {
            console.error('Response error:', error.response.data);
            return Promise.reject(error.response.data);
        } else {
            console.error('Network or other error:', error.message);
            return Promise.reject({
                message: 'Network error or no response was received'
            });
        }
    }
);

export default service;
