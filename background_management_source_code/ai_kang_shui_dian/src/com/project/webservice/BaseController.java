package com.project.webservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.project.bean.others.AjaxResult;
import com.project.bean.others.HtmlStyleBean;
import com.project.utils.TimeUtils;

/**
 * 提取出controller公共方法，作为所有controller的父类
 * 
 * @author 方林
 *
 */
public class BaseController {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ObjectMapper objectMapper;

	public BaseController() {
		objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}

	@ModelAttribute
	public void init(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		response.addHeader("Access-Control-Max-Age", "1800");
		this.request = request;
		this.response = response;
	}

	/**
	 * 将集合类型json字符串转换为java对象
	 * 
	 * @param json
	 * @param collectionClass
	 * @param elementClass
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public Object jsonToObject(String json, Class<?> collectionClass, Class<?> elementClass) {
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClass);
		try {
			return objectMapper.readValue(json, javaType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 简单json对象序列化为java对象
	 * 
	 * @param json
	 * @param elementClass
	 * @return
	 */
	public Object jsonToObject(String json, Class<?> elementClass) {
		try {
			return objectMapper.readValue(json, elementClass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 单独写入消息
	 * 
	 * @param msg
	 * @throws IOException
	 */
	public void WriteOnlyMsg(String msg) {
		this.writeJson(msg);
	}

	/**
	 * 将json中的null替换成"",Bean替换成{},Beans替换成[]
	 * 
	 * @param json
	 * @throws IOException
	 */
	private void writeJson(Object ajaxResult) {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		response.addHeader("Access-Control-Max-Age", "1800");
		try {
			PrintWriter out = response.getWriter();
			out.write(objectMapper.writeValueAsString(ajaxResult).replace("Bean\":null", "Bean\":{}").replace("Beans\":null", "Beans\":[]").replace(":null", ":\"\""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用AjaxResult返回json消息
	 * 
	 * @param response
	 * @param message
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public void WriteMsg(String message) {
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setStatus("ok").setData(message);
		this.writeJson(ajaxResult);
	}

	/**
	 * 返回json对象，带对象数组大小
	 * 
	 * @param response
	 * @param object
	 * @param total
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public void WriteObject(Object object) {
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setStatus("ok");
		ajaxResult.setData(object==null?new Object():object);
		ajaxResult.setTotal(0);
		this.writeJson(ajaxResult);
	}

	public void WriteObject(Object object, int total) {
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setStatus("ok");
		ajaxResult.setData(object==null?new Object():object);
		ajaxResult.setTotal(total);
		this.writeJson(ajaxResult);
	}

	/**
	 * 返回AjaxResult错误json信息
	 * 
	 * @param response
	 * @param error
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public void WriteError(String error) {
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setStatus("error").setError(error);
		this.writeJson(ajaxResult);
	}

	/**
	 * 返回等待AjaxResult对象
	 * 
	 * @param response
	 * @param error
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public void WritePending(String error) {
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setStatus("pending").setError(error);
		this.writeJson(ajaxResult);
	}

	/**
	 * 处理含有图片的接口 返回所有字段的json
	 * 
	 * @param request
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public String getObjectJson(String path) {
		StringBuffer sb = new StringBuffer("{");
		try {
			request.setCharacterEncoding("utf-8");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			List<FileItem> items;
			items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					String key = item.getFieldName();
					String value = item.getString("utf-8");
					sb.append("\"").append(key).append("\":\"").append(value).append("\",");
				} else {
					String key = item.getFieldName();
					String value = item.getName();
					String fileName = System.currentTimeMillis()
							+ value.substring(value.lastIndexOf('.'), value.length()).toLowerCase();
					String basePath = request.getSession().getServletContext().getRealPath("/");
					File f = new File(basePath + path);
					if (!f.exists()) {
						f.mkdirs();
					}
					item.write(new File(basePath + path + "/" + fileName));
					sb.append("\"").append(key).append("\":\"").append(path).append("\\").append(fileName)
							.append("\",");
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
			return "-1";
		} catch (Exception e) {
			e.printStackTrace();
			return "-2";
		}
		return sb.deleteCharAt(sb.length() - 1).append("}").toString();
	}

	/**
	 * 处理含有图片数组的接口 返回所有字段的json
	 * 
	 * @param request
	 * @param response
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getJsonWithImgs() {
		HashMap<String, Object> mapString = new HashMap<String, Object>();
		List<String> mapFile = new ArrayList<String>();
		HashMap<String, Object> result = new HashMap<String, Object>();
		String path = "/images/others/" + TimeUtils.getCurrentTime("yyyyMMdd");
		result.put("state", "0");
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			List<FileItem> items;
			items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					if ("path".equals(item.getFieldName())) {
						path = item.getString("utf-8");
						if (path == null || "".equals(path)) {
							path = "/images/others/" + TimeUtils.getCurrentTime("yyyyMMdd");
						} else if (path.indexOf("%") > -1) {
							path = URLDecoder.decode(path, "utf-8") + "/" + TimeUtils.getCurrentTime("yyyyMMdd");
						}
					}
				}
			}
			for (FileItem item : items) {
				if (item.isFormField()) {
					String key = item.getFieldName();
					String value = item.getString("utf-8");
					mapString.put(key, value);
				} else {
					String value = item.getName();
					String fileName = String.valueOf(System.currentTimeMillis())
							+ String.valueOf(new Random().nextInt(Integer.MAX_VALUE))
							+ value.substring(value.lastIndexOf("."));
					String basePath = request.getSession().getServletContext().getRealPath("/");

					File f = new File(basePath + path);
					if (!f.exists()) {
						f.mkdirs();
					}
					String file = path + "/" + fileName;
					item.write(new File(basePath + file));
					mapFile.add(file);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
			result.put("state", "0");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("state", "0");
			return result;
		}
		try {
			result.put("string", new ObjectMapper().writeValueAsString(mapString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		result.put("file", mapFile);
		result.put("state", "1");
		return result;
	}

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param path
	 * @return
	 */
	public Map<String, Object> uploadFileForm() {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		String file = "";
		String path = "/images/others/" + TimeUtils.getCurrentTime("yyyyMMdd");
		result.put("state", "0");
		try {
			request.setCharacterEncoding("utf-8");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			List<FileItem> items;
			items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					if ("path".equals(item.getFieldName())) {
						path = item.getString("utf-8");
						if (path == null || "".equals(path)) {
							path = "/images/others/" + TimeUtils.getCurrentTime("yyyyMMdd");
						} else if (path.indexOf("%") > -1) {
							path = URLDecoder.decode(path, "utf-8") + "/" + TimeUtils.getCurrentTime("yyyyMMdd");
						}
						break;
					}
				}
			}
			for (FileItem item : items) {
				if (!item.isFormField()) {
					String value = item.getName();
					String fileName = String.valueOf(System.currentTimeMillis())
							+ String.valueOf(new Random().nextInt(Integer.MAX_VALUE))
							+ value.substring(value.lastIndexOf("."));
					String basePath = request.getSession().getServletContext().getRealPath("/");
					File f = new File(basePath + path);
					if (!f.exists()) {
						f.mkdirs();
					}
					file = path + "/" + fileName;
					item.write(new File(basePath + file));
					result.put("state", "1");
				}
			}
			result.put("file", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 读取json字符串
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public String readJSONString() {
		StringBuffer json = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json.toString();
	}

	/**
	 * 读取html内容
	 * 
	 * @param request
	 * @param fileName
	 * @return
	 */
	public String readHtml(String fileName) throws Exception {
		try {
			String basePath = request.getSession().getServletContext().getRealPath("/");
			// 本地html文件路径
			String filePath = basePath + "/" + fileName;
			String templateContent = "";
			FileInputStream fileinputstream = new FileInputStream(filePath);
			int lenght = fileinputstream.available();
			byte bytes[] = new byte[lenght];
			fileinputstream.read(bytes);
			fileinputstream.close();
			templateContent = new String(bytes);
			return templateContent;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 向html文件中写入内容
	 * 
	 * @param request
	 * @param fileName
	 * @param desc
	 * @param htmlStyleBean
	 * @return
	 */
	public boolean writeHtml(String fileName, String desc, HtmlStyleBean htmlStyleBean) {
		try {
			String basePath = request.getSession().getServletContext().getRealPath("/");
			// 模板路径
			String filePath = basePath + "/" + fileName;
			FileOutputStream fileoutputstream = new FileOutputStream(filePath);// 建立文件输出流
			OutputStreamWriter writer = new OutputStreamWriter(fileoutputstream, "utf-8");
			String style = "";
			if (htmlStyleBean == null) {
				style = desc;
			} else {
				style = htmlStyleBean.getStyle_desc();
				int start = desc.indexOf("<content>");
				int end = desc.indexOf("</content>");
				if (start > 0 && end > 0) {
					style = style.replace("[desc]", desc.substring(start + 5, end));
				} else {
					style = style.replace("[desc]", desc);
				}
			}
			byte tag_bytes[] = style.getBytes();
			fileoutputstream.write(tag_bytes);
			writer.flush();
			fileoutputstream.close();
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
