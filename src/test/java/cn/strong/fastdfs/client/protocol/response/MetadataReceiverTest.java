package cn.strong.fastdfs.client.protocol.response;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import org.junit.Test;

import cn.strong.fastdfs.client.Consts;

public class MetadataReceiverTest {

	@Test
	public void test() {
		MetadataReceiver receiver = new MetadataReceiver();
		receiver.toObservable().subscribe(data -> {
			System.out.println("received: ");
			data.toMap().forEach((k, v) -> {
				System.out.println(k + " : " + v);
			});
		}, ex -> {
			ex.printStackTrace();
		}, () -> {
			System.out.println("completed");
		});
		ByteBuf buf = Unpooled.buffer();
		StringBuilder sb = new StringBuilder();
		sb.append("键1");
		sb.append(Consts.FDFS_FIELD_SEPERATOR);
		sb.append("值1");
		sb.append(Consts.FDFS_RECORD_SEPERATOR);
		sb.append("key2");
		sb.append(Consts.FDFS_FIELD_SEPERATOR);
		sb.append("value1");
		byte[] bytes = sb.toString().getBytes();
		buf.writeBytes(bytes);
		
		receiver.setLength(bytes.length);
		receiver.tryRead(buf);
	}

}