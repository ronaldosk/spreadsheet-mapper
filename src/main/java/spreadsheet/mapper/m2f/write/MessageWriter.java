package spreadsheet.mapper.m2f.write;

import spreadsheet.mapper.m2f.write.strategy.MessageWriteStrategy;
import spreadsheet.mapper.model.msg.Message;
import spreadsheet.mapper.model.msg.MessageWriteStrategies;

import java.io.OutputStream;
import java.util.Collection;

/**
 * message writer
 * <p>
 * Created by hanwen on 2017/1/3.
 */
public interface MessageWriter {

  /**
   * <pre>
   * {@link MessageWriteStrategy} unique with {@link MessageWriteStrategies} (one to one),
   * if you add message write strategy with same {@link MessageWriteStrategies},
   * after add will override before add
   * </pre>
   *
   * @param messageWriteStrategies {@link MessageWriteStrategy}
   */
  MessageWriter messageWriteStrategy(MessageWriteStrategy... messageWriteStrategies);

  /**
   * write messages to supplied output stream
   *
   * @param messages     {@link Message}
   * @param outputStream notice close the stream
   */
  void write(Collection<Message> messages, OutputStream outputStream);
}