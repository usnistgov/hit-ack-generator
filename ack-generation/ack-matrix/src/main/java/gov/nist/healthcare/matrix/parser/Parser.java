package gov.nist.healthcare.matrix.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import scala.Option;
import gov.nist.healthcare.matrix.common.Utils;
import gov.nist.healthcare.matrix.model.Component;
import gov.nist.healthcare.matrix.model.Element;
import gov.nist.healthcare.matrix.model.Field;
import gov.nist.healthcare.matrix.model.Group;
import gov.nist.healthcare.matrix.model.Message;
import gov.nist.healthcare.matrix.model.ProfileMatrix;
import gov.nist.healthcare.matrix.model.Segment;
import gov.nist.healthcare.matrix.model.SubComponent;
import hl7.v2.profile.Composite;
import hl7.v2.profile.Datatype;
import hl7.v2.profile.Profile;
import hl7.v2.profile.SegRefOrGroup;
import hl7.v2.profile.XMLDeserializer;

public class Parser {
	
	public ProfileMatrix mtrx;
	
	public ProfileMatrix parse(String profileXml, String messageId){
		mtrx = new ProfileMatrix();
		
		InputStream profileXML = new ByteArrayInputStream(profileXml.getBytes(StandardCharsets.UTF_8));
		Profile p = XMLDeserializer.deserialize(profileXML).get();
		Option<hl7.v2.profile.Message> m = p.messages().get(messageId);
		hl7.v2.profile.Message msg = m.get();
		mtrx.setMessage(createMessage(msg));
		
		return mtrx;
	}
	
	public ProfileMatrix parse(InputStream profileXml, String messageId){
		mtrx = new ProfileMatrix();
		Profile p = XMLDeserializer.deserialize(profileXml).get();
		Option<hl7.v2.profile.Message> m = p.messages().get(messageId);
		hl7.v2.profile.Message msg = m.get();
		mtrx.setMessage(createMessage(msg));
		return mtrx;
	}
	
	public Message createMessage(hl7.v2.profile.Message msg){
		Message _m = new Message(msg.id(),msg.desc());
		scala.collection.Iterator<SegRefOrGroup> iter = msg.structure().iterator();
		int i = 1;
		while(iter.hasNext()){
			SegRefOrGroup sgr = iter.next();
			if(sgr instanceof hl7.v2.profile.Group){
				_m.addSegOrGroup(createGroup((hl7.v2.profile.Group) sgr, null, i));
			}
			else {
				_m.addSegOrGroup(createSegment((hl7.v2.profile.SegmentRef) sgr, null, i));
			}
			i++;
		}
		return _m;
	}
	
	public Group createGroup(hl7.v2.profile.Group grp, Element parent, int id){
		Group _g = new Group(grp.name(),grp.req(), grp.name(), Utils.path(parent, id));
		mtrx.addIndex(_g.getPath(), _g.getInternalPath());
		
		scala.collection.Iterator<SegRefOrGroup> iter = grp.structure().iterator();
		int i = 1;
		while(iter.hasNext()){
			SegRefOrGroup sgr = iter.next();
			if(sgr instanceof hl7.v2.profile.Group){
				_g.addSegOrGroup(createGroup((hl7.v2.profile.Group) sgr, _g, i));
			}
			else {
				_g.addSegOrGroup(createSegment((hl7.v2.profile.SegmentRef) sgr, _g, i));
			}
			i++;
		}
		return _g;
	}
	
	public Segment createSegment(hl7.v2.profile.SegmentRef segr, Element parent, int id){
		hl7.v2.profile.Segment seg = segr.ref();
		Segment _s = new Segment(seg.name(),segr.req(),seg.name(),Utils.path(parent, id));
		mtrx.addIndex(_s.getPath(), _s.getInternalPath());
		
		scala.collection.Iterator<hl7.v2.profile.Field> iter = seg.fields().iterator();
		int i = 1;
		while(iter.hasNext()){
			hl7.v2.profile.Field field = iter.next();
			_s.addField(createField(field,_s.getPath()+"-"+i, _s, i));
			i++;
		}
		
		return _s;
	}
	
	public Field createField(hl7.v2.profile.Field fld, String path, Element parent, int id){
		Field _f = new Field(fld.name(),fld.req(), path, Utils.path(parent, id));
		mtrx.addIndex(_f.getPath(), _f.getInternalPath());
		
		Datatype dt = fld.datatype();
		if(dt instanceof Composite){
			Composite cps = (Composite) dt;
			scala.collection.Iterator<hl7.v2.profile.Component> iter = cps.components().iterator();
			
			int i = 1;
			while(iter.hasNext()){
				hl7.v2.profile.Component component = iter.next();
				_f.addComponent(createComponent(component, _f.getPath()+"."+i, _f, i));
				i++;
			}
		}
		return _f;
	}
	
	public Component createComponent(hl7.v2.profile.Component cmp, String path, Element parent, int id){
		Component _c = new Component(cmp.name(),cmp.req(), path, Utils.path(parent, id));
		mtrx.addIndex(_c.getPath(), _c.getInternalPath());
		
		Datatype dt = cmp.datatype();
		if(dt instanceof Composite){
			Composite cps = (Composite) dt;
			scala.collection.Iterator<hl7.v2.profile.Component> iter = cps.components().iterator();
			
			int i = 1;
			while(iter.hasNext()){
				hl7.v2.profile.Component component = iter.next();
				_c.addSubComponent(createSubComponent(component, _c.getPath()+"."+i, _c, i));
				i++;
			}
			
		}
		return _c;
	}
	
	public SubComponent createSubComponent(hl7.v2.profile.Component cmp, String path, Element parent, int id){
		SubComponent _sc = new SubComponent(cmp.name(),cmp.req(),path, Utils.path(parent, id));
		mtrx.addIndex(_sc.getPath(), _sc.getInternalPath());
		
		return _sc;
	}
}
