package de.fhg.iais.roberta.syntax.sensor.generic;

import de.fhg.iais.roberta.syntax.sensor.ExternalSensor;
import de.fhg.iais.roberta.transformer.forClass.F2M;
import de.fhg.iais.roberta.transformer.forClass.NepoExpr;
import de.fhg.iais.roberta.transformer.forClass.NepoExternalSensor;
import de.fhg.iais.roberta.util.ast.BlocklyBlockProperties;
import de.fhg.iais.roberta.util.ast.BlocklyComment;
import de.fhg.iais.roberta.util.ast.SensorMetaDataBean;

@NepoExpr(name = "HUMIDITY_SENSING", category = "SENSOR", blocklyNames = {"robSensors_humidity_getSample"},
    sampleValues = {@F2M(field = "HUMIDITY_TEMPERATURE", mode = "TEMPERATURE"), @F2M(field = "HUMIDITY_HUMIDITY", mode = "HUMIDITY")})
@NepoExternalSensor()
public final class HumiditySensor<V> extends ExternalSensor<V> {

    public HumiditySensor(BlocklyBlockProperties properties, BlocklyComment comment, SensorMetaDataBean sensorMetaDataBean) {
        super(properties, comment, sensorMetaDataBean);
        setReadOnly();
    }

}
