package com.sgai.pox.engine.core.util.base64;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * @description
 * 				<p>
 *              Base64工具类
 *              </p>
 *
 * @project core
 * @class Base64
 */
public class Base64Util {

	/**
     * 文件读取缓冲区大小
     */
    private static final int CACHE_SIZE = 1024;
    
    /**
     * <p>
     * BASE64字符串解码为二进制数据
     * </p>
     * 
     * @param base64
     * @return
     * @throws Exception
     */
	public static String decode(String str) throws RuntimeException {
		try {
			byte[] bytes = str.getBytes("ASCII");
			return new String(decode(bytes));
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException("ASCII is not supported!", ex);
		}

	}
    
    /**
     * <p>
     * 将文件编码为BASE64字符串
     * </p>
     * <p>
     * 大文件慎用，可能会导致内存溢出
     * </p>
     * 
     * @param filePath 文件绝对路径
     * @return
     * @throws Exception
     */
    public static String encodeFile(String filePath) throws Exception {
        byte[] bytes = fileToByte(filePath);
        return new String(encode(bytes));
    }
    
    
	/**
	 * 将byte数组转换成 base64码str
	 * 
	 * @param bytes
	 * @return
	 * @throws
	 */
	public static String encodeByteToStr(byte[] bytes) throws RuntimeException {
		try {
			return new String(encode(bytes), "ASCII");
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException("ASCII is not supported!", ex);
		}
	}

	/**
	 * base64码str转换成 byte数组
	 * 
	 * @param str
	 * @return
	 * @throws CommonException
	 */
	public static byte[] decodeStrToByte(String str) throws RuntimeException {
		try {
			byte[] bytes = str.getBytes("ASCII");
			return decode(bytes);
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException("ASCII is not supported!", ex);
		}
	}

	/**
	 * 转换str成base64码
	 * 
	 * @param str
	 * @return
	 * @throws CommonException
	 */
	public static String encode(String str) throws RuntimeException {
		byte[] bytes = str.getBytes();
		byte[] encoded = encode(bytes);
		try {
			return new String(encoded, "ASCII");
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException("ASCII is not supported!", ex);
		}
	}

//	/**
//	 * 将base64编码数据解码
//	 * 
//	 * @param str
//	 * @return
//	 * @throws CommonException
//	 */
//	public static String decode(String str) throws RuntimeException {
//		try {
//			byte[] bytes = str.getBytes("ASCII");
//			return new String(decode(bytes));
//		} catch (UnsupportedEncodingException ex) {
//			throw new RuntimeException("ASCII is not supported!", ex);
//		}
//
//	}

	/**
	 * 将原始数组转换成 base64数组
	 * 
	 * @param bytes
	 * @return
	 * @throws CommonException
	 */
	public static byte[] encode(byte[] bytes) throws RuntimeException {
		return encode(bytes, 0);
	}

	/**
	 */
	public static byte[] encode(byte[] bytes, int wrapAt) throws RuntimeException {
		ByteArrayInputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		try {
			inputStream = new ByteArrayInputStream(bytes);
			outputStream = new ByteArrayOutputStream();
			encode(inputStream, outputStream, wrapAt);
			return outputStream.toByteArray();
		} catch (IOException ex) {
			throw new RuntimeException("Unexpected I/O error", ex);
		} finally {
			closeQuietly(inputStream);
			closeQuietly(outputStream);
		}

	}

	/**
	 * 解码byte
	 * 
	 * @param bytes
	 * @return
	 * @throws CommonException
	 */
	public static byte[] decode(byte[] bytes) throws RuntimeException {
		ByteArrayInputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		try {
			inputStream = new ByteArrayInputStream(bytes);
			outputStream = new ByteArrayOutputStream();
			decode(inputStream, outputStream);
			return outputStream.toByteArray();
		} catch (IOException ex) {
			throw new RuntimeException("Unexpected I/O error", ex);
		} finally {
			closeQuietly(inputStream);
			closeQuietly(outputStream);
		}

	}

	/**
	 * 解码流
	 * 
	 * @param inputStream
	 * @param outputStream
	 * @throws IOException
	 */
	public static void encode(InputStream inputStream, OutputStream outputStream) throws IOException {
		encode(inputStream, outputStream, 0);
	}

	/**
	 * 编码流
	 * 
	 * @param inputStream
	 * @param outputStream
	 * @param wrapAt
	 * @throws IOException
	 */
	public static void encode(InputStream inputStream, OutputStream outputStream, int wrapAt) throws IOException {
		Base64OutputStream aux = new Base64OutputStream(outputStream, wrapAt);
		copy(inputStream, aux);
		aux.commit();
	}

	/**
	 * 解码流
	 * 
	 * @param inputStream
	 * @param outputStream
	 * @throws IOException
	 */
	public static void decode(InputStream inputStream, OutputStream outputStream) throws IOException {
		copy(new Base64InputStream(inputStream), outputStream);
	}

	/**
	 * 编码文件
	 * 
	 * @param source
	 * @param target
	 * @param wrapAt
	 * @throws IOException
	 */
	public static void encode(File source, File target, int wrapAt) throws IOException {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(source);
			outputStream = new FileOutputStream(target);
			Base64Util.encode(inputStream, outputStream, wrapAt);
		} finally {
			closeQuietly(inputStream);
			closeQuietly(outputStream);
		}
	}

	/**
	 * 编码文件
	 * 
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void encode(File source, File target) throws IOException {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(source);
			outputStream = new FileOutputStream(target);
			Base64Util.encode(inputStream, outputStream);
		} finally {
			closeQuietly(inputStream);
			closeQuietly(outputStream);
		}
	}

	/**
	 * 解码文件
	 * 
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void decode(File source, File target) throws IOException {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(source);
			outputStream = new FileOutputStream(target);
			decode(inputStream, outputStream);
		} finally {
			closeQuietly(inputStream);
			closeQuietly(outputStream);
		}
	}

	/**
	 * copy stream
	 * 
	 * @param inputStream
	 * @param outputStream
	 * @throws IOException
	 */
	private static void copy(InputStream inp, OutputStream out) throws IOException {
		byte[] buff = new byte[4096];
		int count;
		while ((count = inp.read(buff)) != -1) {
			if (count > 0) {
				out.write(buff, 0, count);
			}
		}
	}

	protected static void closeQuietly(final Closeable closeable) {
		try {
			closeable.close();
		} catch (Exception exc) {
		}
	}
    
    /**
     * <p>
     * 文件转换为二进制数组
     * </p>
     * 
     * @param filePath 文件路径
     * @return
     * @throws Exception
     */
    public static byte[] fileToByte(String filePath) throws Exception {
        byte[] data = new byte[0];
        File file = new File(filePath);
        if (file.exists()) {
            FileInputStream in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
            byte[] cache = new byte[CACHE_SIZE];
            int nRead = 0;
            while ((nRead = in.read(cache)) != -1) {
                out.write(cache, 0, nRead);
                out.flush();
            }
            out.close();
            in.close();
            data = out.toByteArray();
         }
        return data;
    }
    
    /**
     * <p>
     * 二进制数据写文件
     * </p>
     * 
     * @param bytes 二进制数据
     * @param filePath 文件生成目录
     */
    public static void byteArrayToFile(byte[] bytes, String filePath) throws Exception {
        InputStream in = new ByteArrayInputStream(bytes);   
        File destFile = new File(filePath);
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        destFile.createNewFile();
        OutputStream out = new FileOutputStream(destFile);
        byte[] cache = new byte[CACHE_SIZE];
        int nRead = 0;
        while ((nRead = in.read(cache)) != -1) {   
            out.write(cache, 0, nRead);
            out.flush();
        }
        out.close();
        in.close();
    }
	
    public static String bytesToBase64(byte[] bytes) {
        return org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);// 返回Base64编码过的字节数组字符串
    }
 
    /**
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param path 图片路径
     * @return base64字符串
     */
    public static String imageToBase64(String path) throws IOException {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        InputStream in = null;
        try {
            in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return org.apache.commons.codec.binary.Base64.encodeBase64String(data);// 返回Base64编码过的字节数组字符串
    }
 
    /**
     * 处理Base64解码并写图片到指定位置
     *
     * @param base64 图片Base64数据
     * @param path   图片保存路径
     * @return
     */
    public static boolean base64ToImageFile(String base64, String path) throws IOException {// 对字节数组字符串进行Base64解码并生成图片
        // 生成jpeg图片
        try {
            OutputStream out = new FileOutputStream(path);
            return base64ToImageOutput(base64, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
 
    /**
     * 处理Base64解码并输出流
     *
     * @param base64
     * @param out
     * @return
     */
    public static boolean base64ToImageOutput(String base64, OutputStream out) throws IOException {
        if (base64 == null) { // 图像数据为空
            return false;
        }
        try {
            // Base64解码
            byte[] bytes = org.apache.commons.codec.binary.Base64.decodeBase64(base64);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            out.write(bytes);
            out.flush();
            return true;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
