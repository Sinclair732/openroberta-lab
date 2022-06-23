package de.fhg.iais.roberta.syntax.sensor.generic;

import java.util.List;

import de.fhg.iais.roberta.blockly.generated.Block;
import de.fhg.iais.roberta.blockly.generated.Field;
import de.fhg.iais.roberta.factory.BlocklyDropdownFactory;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.syntax.sensor.ExternalSensor;
import de.fhg.iais.roberta.transformer.Ast2Jaxb;
import de.fhg.iais.roberta.transformer.Jaxb2Ast;
import de.fhg.iais.roberta.transformer.Jaxb2ProgramAst;
import de.fhg.iais.roberta.transformer.forClass.NepoBasic;
import de.fhg.iais.roberta.transformer.forClass.F2M;
import de.fhg.iais.roberta.util.ast.BlocklyBlockProperties;
import de.fhg.iais.roberta.util.ast.BlocklyComment;
import de.fhg.iais.roberta.util.ast.SensorMetaDataBean;
import de.fhg.iais.roberta.util.syntax.BlocklyConstants;

/**
 * This class represents the <b>robSensors_timer_reset</b> and <b>robSensors_timer_getSample</b> blocks from Blockly into the AST (abstract syntax tree). Object
 * from this class will generate code for reset the sensor or getting a sample from the sensor.<br/>
 * <br>
 * The client must provide the {@link SensorPort} and {@link TimerSensorMode}. See enum {@link TimerSensorMode} for all possible modes of the sensor.<br>
 * <br>
 * To create an instance from this class use the method {@link #make(TimerSensorMode, int, BlocklyBlockProperties, BlocklyComment)}.<br>
 */
@NepoBasic(sampleValues = {@F2M(field = "TIMER_VALUE", mode = "VALUE"), @F2M(field = "TIME", mode = "VALUE")}, name = "TIMER_SENSING", category = "SENSOR", blocklyNames = {"mbedSensors_timer_reset", "robSensors_timer_getSample", "robSensors_timer_reset"})
public final class TimerSensor<V> extends ExternalSensor<V> {

    public TimerSensor(BlocklyBlockProperties properties, BlocklyComment comment, SensorMetaDataBean sensorMetaDataBean) {
        super(properties, comment, sensorMetaDataBean);
        setReadOnly();
    }

    public static <V> Phrase<V> jaxbToAst(Block block, Jaxb2ProgramAst<V> helper) {
        BlocklyDropdownFactory factory = helper.getDropdownFactory();
        SensorMetaDataBean sensorMetaDataBean;
        //TODO This if statement should be removed when we have new implementation of reset sensor blockly block
        if ( block.getType().equals(BlocklyConstants.ROB_SENSORS_TIMER_RESET) || block.getType().equals(BlocklyConstants.ROB_SENSORS_TIMER_RESET_CALLIOPE) ) {
            List<Field> fields = Jaxb2Ast.extractFields(block, (short) 1);
            String portName = Jaxb2Ast.extractField(fields, BlocklyConstants.SENSORPORT);
            sensorMetaDataBean =
                new SensorMetaDataBean(Jaxb2Ast.sanitizePort(portName), factory.getMode("RESET"), Jaxb2Ast.sanitizeSlot(BlocklyConstants.NO_SLOT), null);
            return new TimerSensor<>(Jaxb2Ast.extractBlockProperties(block), Jaxb2Ast.extractComment(block), sensorMetaDataBean);
        }
        sensorMetaDataBean = extractPortAndModeAndSlot(block, helper);
        return new TimerSensor<>(Jaxb2Ast.extractBlockProperties(block), Jaxb2Ast.extractComment(block), sensorMetaDataBean);
    }

    @Override
    public Block astToBlock() {
        if ( getMode().toString().equals("RESET") ) {
            Block jaxbDestination = new Block();
            Ast2Jaxb.setBasicProperties(this, jaxbDestination);
            String fieldValue = getUserDefinedPort();
            Ast2Jaxb.addField(jaxbDestination, BlocklyConstants.SENSORPORT, fieldValue);
            return jaxbDestination;
        } else {
            return super.astToBlock();
        }

    }

}
