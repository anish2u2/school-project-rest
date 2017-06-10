package school.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import school.abstracts.AbstractGenericSchoolService;
import school.contracts.ParentService;
import school.dao.ParentDataAccess;
import school.dto.Parent;
import school.pojo.Parents;

@Component
@Service
@Qualifier("parentsService")
public class ParentsServiceImpl extends AbstractGenericSchoolService implements ParentService {

	@Autowired
	@Qualifier("parentDataAccess")
	private ParentDataAccess parentDataAccess;

	@Transactional(readOnly = true)
	public Parents fetchParent(Long parentId) {
		Parent parentDetial = parentDataAccess.get(parentId);
		Parents parent = new Parents();
		if (parentDetial != null) {
			parent.setParentId(parentDetial.getId());
			parent.setParentName(parentDetial.getName());
		}
		return parent;
	}

	@Override
	@Transactional(readOnly = true)
	protected Object fetchAll(Long schoolId) {
		List<school.dto.Parent> parentList = (List<school.dto.Parent>) parentDataAccess.fetchAll();
		List<Parents> result = new ArrayList<Parents>();
		for (school.dto.Parent parentData : parentList) {
			Parents parent = new Parents();
			parent.setParentId(parentData.getId());
			parent.setParentName(parentData.getName());
			result.add(parent);
		}
		return result;
	}

}
