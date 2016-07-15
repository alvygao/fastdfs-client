/**
 * 
 */
package cn.strong.fastdfs.client.protocol.request.tracker;

import static cn.strong.fastdfs.client.CommandCodes.TRACKER_PROTO_CMD_SERVICE_QUERY_STORE_WITHOUT_GROUP_ONE;
import static cn.strong.fastdfs.client.CommandCodes.TRACKER_PROTO_CMD_SERVICE_QUERY_STORE_WITH_GROUP_ONE;
import static cn.strong.fastdfs.client.Consts.ERRNO_OK;
import static cn.strong.fastdfs.client.Consts.FDFS_GROUP_LEN;
import static cn.strong.fastdfs.client.Consts.HEAD_LEN;
import static cn.strong.fastdfs.utils.Utils.isEmpty;
import static cn.strong.fastdfs.utils.Utils.writeFixLength;
import static java.nio.charset.StandardCharsets.UTF_8;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

import cn.strong.fastdfs.client.protocol.request.Request;

/**
 * 获取可上传的存储服务器
 * 
 * @author liulongbiao
 *
 */
public class GetUploadStorageRequest implements Request {

	private String group;

	public GetUploadStorageRequest(String group) {
		this.group = group;
	}

	@Override
	public void encode(ChannelHandlerContext ctx, List<Object> out) {
		int length = isEmpty(group) ? 0 : FDFS_GROUP_LEN;
		byte cmd = isEmpty(group) ? TRACKER_PROTO_CMD_SERVICE_QUERY_STORE_WITHOUT_GROUP_ONE
				: TRACKER_PROTO_CMD_SERVICE_QUERY_STORE_WITH_GROUP_ONE;
		ByteBuf buf = ctx.alloc().buffer(length + HEAD_LEN);
		buf.writeLong(length);
		buf.writeByte(cmd);
		buf.writeByte(ERRNO_OK);
		if (!isEmpty(group)) {
			writeFixLength(buf, group, FDFS_GROUP_LEN, UTF_8);
		}
		out.add(buf);
	}

}
