/**
 * 
 */
package cn.strong.fastdfs.client.protocol.request.tracker;

import static cn.strong.fastdfs.client.CommandCodes.TRACKER_PROTO_CMD_SERVICE_QUERY_UPDATE;

import cn.strong.fastdfs.client.protocol.request.AbstractStoragePathRequest;
import cn.strong.fastdfs.model.StoragePath;

/**
 * 获取可更新的存储服务器
 * 
 * @author liulongbiao
 *
 */
public class GetUpdateStorageRequest extends AbstractStoragePathRequest {

	public GetUpdateStorageRequest(StoragePath spath) {
		super(spath);
	}

	@Override
	protected byte cmd() {
		return TRACKER_PROTO_CMD_SERVICE_QUERY_UPDATE;
	}

}
